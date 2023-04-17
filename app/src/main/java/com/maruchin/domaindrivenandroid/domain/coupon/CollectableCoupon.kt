package com.maruchin.domaindrivenandroid.domain.coupon

import com.maruchin.domaindrivenandroid.data.coupon.Coupon
import com.maruchin.domaindrivenandroid.data.coupon.sampleCoupons

data class CollectableCoupon(val coupon: Coupon, val canCollect: Boolean)

val sampleCollectableCoupons = listOf(
    CollectableCoupon(coupon = sampleCoupons[2], canCollect = true),
    CollectableCoupon(coupon = sampleCoupons[1], canCollect = true),
    CollectableCoupon(coupon = sampleCoupons[0], canCollect = false),
)
