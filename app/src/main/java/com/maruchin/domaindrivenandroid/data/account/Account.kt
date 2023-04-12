package com.maruchin.domaindrivenandroid.data.account

import com.maruchin.domaindrivenandroid.data.coupon.Coupon
import com.maruchin.domaindrivenandroid.data.units.Points

data class Account(
    val username: String,
    val email: String,
    val collectedPoints: Points,
) {

    fun canPayForCoupon(coupon: Coupon): Boolean {
        return collectedPoints >= coupon.price
    }

    fun payForCoupon(coupon: Coupon): Account {
        check(canPayForCoupon(coupon))
        return copy(collectedPoints = collectedPoints - coupon.price)
    }
}

val sampleAccount = Account(
    username = "Marcin",
    email = "marcinpk.mp@gmail.com",
    collectedPoints = Points(100),
)
