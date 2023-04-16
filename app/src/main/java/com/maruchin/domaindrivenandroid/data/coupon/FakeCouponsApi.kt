package com.maruchin.domaindrivenandroid.data.coupon

class FakeCouponsApi : CouponsApi {

    override suspend fun fetchAllCoupons(): List<CouponJson> {
        return sampleCouponsJson
    }
}
