package com.maruchin.domaindrivenandroid.ui

import com.maruchin.domaindrivenandroid.data.coupon.Coupon
import com.maruchin.domaindrivenandroid.data.units.ID
import com.maruchin.domaindrivenandroid.data.units.Points
import com.maruchin.domaindrivenandroid.domain.coupon.CollectableCoupon
import java.net.URL

fun placeholderCollectableCoupon(index: Int = 0) = CollectableCoupon(
    coupon = Coupon(
        id = ID(index.toString()),
        name = "00000 00000 00000 00000 00000",
        points = Points(1000),
        image = URL("http://empty.placeholder"),
    ),
    canCollect = true,
)
