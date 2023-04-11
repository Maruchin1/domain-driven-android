package com.maruchin.domaindrivenandroid.data.coupon

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CouponsRepository @Inject constructor() {

    suspend fun getAllCoupons(): List<Coupon> {
        return sampleCoupons
    }
}
