package com.maruchin.domaindrivenandroid.data.coupon.api

class FakeCouponsApi : CouponsApi {

    override suspend fun fetchAllCoupons(): List<CouponJson> {
        return sampleCouponsJson
    }
}
