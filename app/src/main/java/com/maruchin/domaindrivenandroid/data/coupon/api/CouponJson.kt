package com.maruchin.domaindrivenandroid.data.coupon.api

import com.google.gson.annotations.SerializedName
import com.maruchin.domaindrivenandroid.data.coupon.Coupon
import com.maruchin.domaindrivenandroid.data.values.ID
import com.maruchin.domaindrivenandroid.data.values.Name
import com.maruchin.domaindrivenandroid.data.values.Points
import java.net.URL

data class CouponJson(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("points")
    val points: Int,
    @SerializedName("image")
    val image: String,
)

fun CouponJson.toModel() = Coupon(
    id = ID(id),
    name = Name(name),
    points = Points(points),
    image = URL(image),
    activationCode = null,
)

fun List<CouponJson>.toModel() = map { it.toModel() }

val sampleCouponsJson = listOf(
    CouponJson(
        id = "1",
        name = "Cheesburger with fries",
        points = 200,
        image = "https://raw.githubusercontent.com/Maruchin1/domain-driven-android/master/images/cheesburger_with_fries_coupon.jpeg",
    ),
    CouponJson(
        id = "2",
        name = "2 x Milkshake",
        points = 100,
        image = "https://raw.githubusercontent.com/Maruchin1/domain-driven-android/master/images/two_milkshakes_coupon.jpeg",
    ),
    CouponJson(
        id = "3",
        name = "2 x Soda drink",
        points = 50,
        image = "https://raw.githubusercontent.com/Maruchin1/domain-driven-android/master/images/two_soda_drinks_coupon.jpeg",
    ),
)
