package com.maruchin.domaindrivenandroid.domain.coupon

import com.maruchin.domaindrivenandroid.data.units.ID
import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import javax.inject.Inject

class UnlockCouponUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val couponsRepository: CouponsRepository,
) {

    suspend operator fun invoke(couponId: ID) {
        val account = accountRepository.getLoggedInAccount().let(::checkNotNull)
        val selectedCoupon = couponsRepository.getCoupon(couponId).let(::checkNotNull)
        val updatedAccount = account.payForCoupon(selectedCoupon)
        val updatedCoupon = selectedCoupon.unlock()
        accountRepository.saveLoggedInAccount(updatedAccount)
        couponsRepository.saveCoupon(updatedCoupon)
    }
}
