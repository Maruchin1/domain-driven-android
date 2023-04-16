package com.maruchin.domaindrivenandroid.domain.coupon

import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.activationCode.ActivationCodesRepository
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import com.maruchin.domaindrivenandroid.data.units.ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class CollectCouponUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val couponsRepository: CouponsRepository,
    private val activationCodesRepository: ActivationCodesRepository,
    private val scope: CoroutineScope,
) {

    suspend operator fun invoke(couponId: ID) {
        var account = accountRepository.getLoggedInAccount().first().let(::checkNotNull)
        var coupon = couponsRepository.getCoupon(couponId).first().let(::checkNotNull)
        account = account.payFor(coupon)
        val activationCode = activationCodesRepository.getActivationCodeForCoupon(couponId)
        coupon = coupon.collect(activationCode)
        accountRepository.saveLoggedInAccount(account)
        couponsRepository.updateCoupon(coupon)
        scope.launch {
            while (coupon.canActivate) {
                coupon = coupon.waitForActivation()
                couponsRepository.updateCoupon(coupon)
            }
            coupon = coupon.reset()
            couponsRepository.updateCoupon(coupon)
        }
    }
}
