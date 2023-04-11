package com.maruchin.domaindrivenandroid.data.coupon

import com.maruchin.domaindrivenandroid.data.ID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CouponsRepository @Inject constructor() {

    suspend fun getAllCoupons(): List<Coupon> {
        return sampleCoupons
    }

    suspend fun getCoupon(id: ID): Coupon? {
        return sampleCoupons.find { it.id == id }
    }
}
