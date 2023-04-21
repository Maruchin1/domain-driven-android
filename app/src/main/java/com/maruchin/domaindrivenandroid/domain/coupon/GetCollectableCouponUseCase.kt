package com.maruchin.domaindrivenandroid.domain.coupon

import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import com.maruchin.domaindrivenandroid.data.values.ID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

class GetCollectableCouponUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val couponsRepository: CouponsRepository,
) {

    operator fun invoke(couponId: ID): Flow<CollectableCoupon?> {
        return combine(
            accountRepository.getLoggedInAccount().filterNotNull(),
            couponsRepository.getCoupon(couponId).filterNotNull(),
        ) { account, coupon ->
            CollectableCoupon(
                coupon = coupon,
                canCollect = account.canExchangePointsFor(coupon)
            )
        }
    }
}
