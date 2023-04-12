package com.maruchin.domaindrivenandroid.data.coupon

import retrofit2.http.GET

interface CouponsApi {

    @GET("coupons.json")
    suspend fun fetchAllCoupons(): List<CouponJson>
}
