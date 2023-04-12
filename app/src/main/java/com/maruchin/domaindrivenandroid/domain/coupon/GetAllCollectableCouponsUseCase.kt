package com.maruchin.domaindrivenandroid.domain.coupon

import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import javax.inject.Inject

class GetAllCollectableCouponsUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val couponsRepository: CouponsRepository,
) {

    suspend operator fun invoke(): List<CollectableCoupon> {
        val account = accountRepository.getLoggedInAccount().let(::checkNotNull)
        return couponsRepository.getAllCoupons().sortedBy { coupon ->
            coupon.price
        }.map { coupon ->
            CollectableCoupon(coupon = coupon, canCollect = account.canPayForCoupon(coupon))
        }
    }
}
