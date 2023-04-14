package com.maruchin.domaindrivenandroid.domain.coupon

import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import com.maruchin.domaindrivenandroid.data.units.ID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCollectableCouponUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val couponsRepository: CouponsRepository,
) {

    operator fun invoke(couponId: ID): Flow<CollectableCoupon?> {
        return accountRepository.getLoggedInAccount().filterNotNull().flatMapLatest { account ->
            couponsRepository.getCoupon(couponId).filterNotNull().map { coupon ->
                CollectableCoupon(
                    coupon = coupon,
                    canCollect = account.canPayFor(coupon)
                )
            }
        }
    }
}
