package com.maruchin.domaindrivenandroid.domain.coupon

import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

class GetAllCollectableCouponsUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val couponsRepository: CouponsRepository,
) {

    operator fun invoke(): Flow<List<CollectableCoupon>> {
        return combine(
            accountRepository.getLoggedInAccount().filterNotNull(),
            couponsRepository.getAllCoupons()
        ) { account, allCoupons ->
            allCoupons.sortedBy {
                it.points
            }.map { coupon ->
                CollectableCoupon(coupon = coupon, canCollect = account.canPayFor(coupon))
            }
        }
    }
}
