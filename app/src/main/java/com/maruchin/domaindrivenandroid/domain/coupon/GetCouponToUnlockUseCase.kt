package com.maruchin.domaindrivenandroid.domain.coupon

import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import com.maruchin.domaindrivenandroid.data.units.ID
import javax.inject.Inject

class GetCouponToUnlockUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val couponsRepository: CouponsRepository,
) {

    suspend operator fun invoke(couponId: ID): CouponToUnlock? {
        val account = accountRepository.getLoggedInAccount().let(::checkNotNull)
        return couponsRepository.getCoupon(couponId)?.let { coupon ->
            CouponToUnlock(coupon = coupon, canUnlock = account.canPayForCoupon(coupon))
        }
    }
}
