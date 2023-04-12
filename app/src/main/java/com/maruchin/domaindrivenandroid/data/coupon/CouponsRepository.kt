package com.maruchin.domaindrivenandroid.data.coupon

import com.maruchin.domaindrivenandroid.data.ID
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CouponsRepository @Inject constructor() {

    suspend fun getAllCoupons(): List<Coupon> {
        delay(2_000)
        return sampleCoupons
    }

    suspend fun getCoupon(id: ID): Coupon? {
        delay(1_000)
        return sampleCoupons.find { it.id == id }
    }
}
