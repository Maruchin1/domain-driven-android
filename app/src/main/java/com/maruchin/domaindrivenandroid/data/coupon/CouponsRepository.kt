package com.maruchin.domaindrivenandroid.data.coupon

import com.maruchin.domaindrivenandroid.data.units.ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CouponsRepository @Inject constructor(
    private val couponsApi: CouponsApi,
    private val scope: CoroutineScope,
) {

    private val coupons: SharedFlow<List<Coupon>> = flow {
        emit(couponsApi.fetchAllCoupons())
    }.map { json ->
        json.toModel()
    }.shareIn(scope, SharingStarted.Lazily, replay = 1)

    fun getAllCoupons(): Flow<List<Coupon>> {
        return coupons
    }

    fun getCoupon(id: ID): Flow<Coupon?> {
        return coupons.map { coupons ->
            coupons.find { it.id == id }
        }
    }
}
