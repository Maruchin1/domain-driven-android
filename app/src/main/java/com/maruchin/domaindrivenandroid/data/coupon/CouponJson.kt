package com.maruchin.domaindrivenandroid.data.coupon

import com.google.gson.annotations.SerializedName

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
