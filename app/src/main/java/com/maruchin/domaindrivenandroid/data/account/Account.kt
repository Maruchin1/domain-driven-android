package com.maruchin.domaindrivenandroid.data.account

import com.maruchin.domaindrivenandroid.data.coupon.Coupon
import com.maruchin.domaindrivenandroid.data.units.Points

data class Account(
    val username: String,
    val email: String,
    val collectedPoints: Points,
) {

    fun canPayFor(coupon: Coupon): Boolean {
        return collectedPoints >= coupon.points
    }

    fun payFor(coupon: Coupon): Account {
        check(canPayFor(coupon))
        return copy(collectedPoints = collectedPoints - coupon.points)
    }
}

val sampleAccount = Account(
    username = "Marcin",
    email = "marcinpk.mp@gmail.com",
    collectedPoints = Points(100),
)
