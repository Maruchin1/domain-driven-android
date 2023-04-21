package com.maruchin.domaindrivenandroid.domain.coupon

import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import com.maruchin.domaindrivenandroid.data.coupon.factory.ActivationCodeFactory
import com.maruchin.domaindrivenandroid.data.values.ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class CollectCouponUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val couponsRepository: CouponsRepository,
    private val activationCodeFactory: ActivationCodeFactory,
    private val scope: CoroutineScope,
) {

    suspend operator fun invoke(couponId: ID) {
        var account = checkNotNull(accountRepository.getLoggedInAccount().first())
        var coupon = checkNotNull(couponsRepository.getCoupon(couponId).first())
        account = account.exchangePointsFor(coupon)
        val activationCode = activationCodeFactory.createRandomActivationCode()
        coupon = coupon.collect(activationCode)
        accountRepository.updateLoggedInAccount(account)
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
