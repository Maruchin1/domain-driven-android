package com.maruchin.domaindrivenandroid.data.coupon

import com.maruchin.domaindrivenandroid.data.units.ID
import com.maruchin.domaindrivenandroid.data.units.Points
import java.net.URL

data class Coupon(
    val id: ID,
    val name: String,
    val points: Points,
    val image: URL,
)
