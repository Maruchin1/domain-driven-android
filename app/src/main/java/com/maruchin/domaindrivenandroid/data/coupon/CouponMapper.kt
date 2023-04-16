package com.maruchin.domaindrivenandroid.data.coupon

import com.maruchin.domaindrivenandroid.data.units.ID
import com.maruchin.domaindrivenandroid.data.units.Points
import java.net.URL

fun CouponJson.toModel() = Coupon(
    id = ID(id),
    name = name,
    points = Points(points),
    image = URL(image),
    activationCode = null
)

fun List<CouponJson>.toModel() = map { it.toModel() }
