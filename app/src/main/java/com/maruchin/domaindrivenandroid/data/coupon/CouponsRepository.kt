package com.maruchin.domaindrivenandroid.data.coupon

import com.maruchin.domaindrivenandroid.data.units.ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.sync.Mutex
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CouponsRepository @Inject constructor(
    private val couponsApi: CouponsApi,
    private val scope: CoroutineScope,
) : Mutex by Mutex() {
    private var coupons: Map<ID, Coupon> = emptyMap()

    suspend fun getAllCoupons(): List<Coupon> {
        loadIfCacheEmpty()
        return coupons.values.toList()
    }

    suspend fun getCoupon(id: ID): Coupon? {
        loadIfCacheEmpty()
        return coupons[id]
    }

    private suspend fun loadIfCacheEmpty() {
        if (coupons.isEmpty()) {
            scope.async {
                coupons = couponsApi.fetchAllCoupons().toModel().associateBy { it.id }
            }.await()
        }
    }
}
