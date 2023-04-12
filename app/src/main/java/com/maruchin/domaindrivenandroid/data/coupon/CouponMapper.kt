package com.maruchin.domaindrivenandroid.data.coupon

import com.maruchin.domaindrivenandroid.data.units.ID
import com.maruchin.domaindrivenandroid.data.units.Points
import java.net.URL

fun CouponJson.toModel() = Coupon(
    id = ID(id),
    name = name,
    price = Points(points),
    image = URL(image),
)

fun List<CouponJson>.toModel() = this.map { it.toModel() }
