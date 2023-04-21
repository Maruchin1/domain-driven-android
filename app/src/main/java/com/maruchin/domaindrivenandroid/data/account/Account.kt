package com.maruchin.domaindrivenandroid.data.account

import com.maruchin.domaindrivenandroid.data.coupon.Coupon
import com.maruchin.domaindrivenandroid.data.values.Email
import com.maruchin.domaindrivenandroid.data.values.Points

data class Account(
    val email: Email,
    val collectedPoints: Points,
) {

    fun canExchangePointsFor(coupon: Coupon): Boolean {
        return collectedPoints >= coupon.points
    }

    fun exchangePointsFor(coupon: Coupon): Account {
        check(canExchangePointsFor(coupon))
        return copy(collectedPoints = collectedPoints - coupon.points)
    }
}

val sampleAccount = Account(
    email = Email("marcinpk.mp@gmail.com"),
    collectedPoints = Points(170),
)
