package com.maruchin.domaindrivenandroid.domain.coupon

import com.maruchin.domaindrivenandroid.data.coupon.Coupon

data class CouponToUnlock(val coupon: Coupon, val canUnlock: Boolean)
