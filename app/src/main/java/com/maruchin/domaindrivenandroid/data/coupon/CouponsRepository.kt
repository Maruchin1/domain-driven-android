package com.maruchin.domaindrivenandroid.data.coupon

import com.maruchin.domaindrivenandroid.data.units.ID
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CouponsRepository @Inject constructor() : Mutex by Mutex() {
    private val _coupons: MutableMap<ID, Coupon> =
        sampleCoupons.associateBy { it.id }.toMutableMap()

    suspend fun getAllCoupons(): List<Coupon> = withLock {
        delay(2_000)
        _coupons.values.toList()
    }

    suspend fun getCoupon(id: ID): Coupon? = withLock {
        delay(1_000)
        _coupons[id]
    }

    suspend fun saveCoupon(coupon: Coupon) = withLock {
        delay(1_000)
        _coupons[coupon.id] = coupon
    }
}
