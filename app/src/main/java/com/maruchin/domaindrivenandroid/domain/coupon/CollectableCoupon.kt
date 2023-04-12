package com.maruchin.domaindrivenandroid.domain.coupon

import com.maruchin.domaindrivenandroid.data.coupon.Coupon

data class CollectableCoupon(val coupon: Coupon, val canCollect: Boolean)
