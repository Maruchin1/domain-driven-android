package com.maruchin.domaindrivenandroid.data.coupon.api

import com.maruchin.domaindrivenandroid.data.coupon.sampleCoupons
import org.junit.Assert.*
import org.junit.Test

class CouponJsonTest {

    @Test
    fun `Map json to model`() {
        val coupon = sampleCouponsJson[1].toModel()

        assertEquals(sampleCoupons[1], coupon)
    }

    @Test
    fun `Map json list to model`() {
        val coupons = sampleCouponsJson.toModel()

        assertEquals(sampleCoupons, coupons)
    }
}
