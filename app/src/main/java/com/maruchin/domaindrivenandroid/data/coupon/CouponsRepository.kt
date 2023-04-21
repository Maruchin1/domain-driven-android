package com.maruchin.domaindrivenandroid.data.coupon

import com.maruchin.domaindrivenandroid.data.coupon.api.CouponsApi
import com.maruchin.domaindrivenandroid.data.coupon.api.toModel
import com.maruchin.domaindrivenandroid.data.values.ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CouponsRepository @Inject constructor(
    private val couponsApi: CouponsApi,
    private val scope: CoroutineScope,
) {
    private val coupons = MutableStateFlow<Map<ID, Coupon>>(emptyMap())

    fun getAllCoupons(): Flow<List<Coupon>> {
        return coupons.map { collection ->
            collection.values.toList()
        }.onStart {
            fetchCouponsIfNotAvailableLocallyAsync().await()
        }
    }

    fun getCoupon(id: ID): Flow<Coupon?> {
        return coupons.map { collection ->
            collection[id]
        }.onStart {
            fetchCouponsIfNotAvailableLocallyAsync().await()
        }
    }

    suspend fun updateCoupon(coupon: Coupon) {
        val updatedCoupons = coupons.value + (coupon.id to coupon)
        coupons.emit(updatedCoupons)
    }

    private fun fetchCouponsIfNotAvailableLocallyAsync() = scope.async {
        if (coupons.value.isEmpty()) {
            val couponsFromApi = couponsApi.fetchAllCoupons().toModel().associateBy { it.id }
            coupons.emit(couponsFromApi)
        }
    }
}
