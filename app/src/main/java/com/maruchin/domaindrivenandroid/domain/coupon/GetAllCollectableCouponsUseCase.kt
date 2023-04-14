package com.maruchin.domaindrivenandroid.domain.coupon

import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllCollectableCouponsUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val couponsRepository: CouponsRepository,
) {

    operator fun invoke(): Flow<List<CollectableCoupon>> {
        return accountRepository.getLoggedInAccount().filterNotNull().flatMapLatest { account ->
            couponsRepository.getAllCoupons().map { allCoupons ->
                allCoupons.map { coupon ->
                    CollectableCoupon(
                        coupon = coupon,
                        canCollect = account.canPayFor(coupon)
                    )
                }
            }
        }
    }
}
