package com.maruchin.domaindrivenandroid.domain.coupon

import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.activationCode.ActivationCode
import com.maruchin.domaindrivenandroid.data.activationCode.ActivationCodesRepository
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import com.maruchin.domaindrivenandroid.data.units.ID
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CollectCouponUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val couponsRepository: CouponsRepository,
    private val activationCodesRepository: ActivationCodesRepository,
) {

    suspend operator fun invoke(couponId: ID): ActivationCode {
        val account = accountRepository.getLoggedInAccount().first().let(::checkNotNull)
        val selectedCoupon = couponsRepository.getCoupon(couponId).first().let(::checkNotNull)
        val updatedAccount = account.payFor(selectedCoupon)
        val activationCode = activationCodesRepository.getActivationCodeForCoupon(couponId)
        accountRepository.saveLoggedInAccount(updatedAccount)
        return activationCode
    }
}
