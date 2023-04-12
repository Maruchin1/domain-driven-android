package com.maruchin.domaindrivenandroid.domain.coupon

import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import javax.inject.Inject

class GetAllCouponsToUnlockUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val couponsRepository: CouponsRepository,
) {

    suspend operator fun invoke(): List<CouponToUnlock> {
        val account = accountRepository.getLoggedInAccount().let(::checkNotNull)
        return couponsRepository.getAllCoupons().sortedBy { coupon ->
            coupon.price
        }.map { coupon ->
            CouponToUnlock(coupon = coupon, canUnlock = account.canPayForCoupon(coupon))
        }
    }
}
