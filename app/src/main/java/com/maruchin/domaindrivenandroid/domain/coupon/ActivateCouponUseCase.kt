package com.maruchin.domaindrivenandroid.domain.coupon

import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.activationCode.ActivationCode
import com.maruchin.domaindrivenandroid.data.activationCode.ActivationCodesRepository
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import com.maruchin.domaindrivenandroid.data.units.ID
import javax.inject.Inject

class ActivateCouponUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val couponsRepository: CouponsRepository,
    private val activationCodesRepository: ActivationCodesRepository,
) {

    suspend operator fun invoke(couponId: ID): ActivationCode {
        val account = accountRepository.getLoggedInAccount().let(::checkNotNull)
        val selectedCoupon = couponsRepository.getCoupon(couponId).let(::checkNotNull)
        val updatedAccount = account.payForCoupon(selectedCoupon)
        accountRepository.saveLoggedInAccount(updatedAccount)
        return activationCodesRepository.getActivationCodeForCoupon(couponId)
    }
}
