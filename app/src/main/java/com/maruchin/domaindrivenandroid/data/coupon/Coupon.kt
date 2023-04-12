package com.maruchin.domaindrivenandroid.data.coupon

import com.maruchin.domaindrivenandroid.data.units.ID
import com.maruchin.domaindrivenandroid.data.units.Points
import java.net.URL

data class Coupon(
    val id: ID,
    val name: String,
    val price: Points,
    val image: URL,
    val unlocked: Boolean,
) {

    fun unlock(): Coupon {
        check(!unlocked)
        return copy(unlocked = true)
    }
}

val sampleCoupons = listOf(
    Coupon(
        id = ID("1"),
        name = "Cheesburger with fries",
        price = Points(200),
        image = URL("https://raw.githubusercontent.com/Maruchin1/domain-driven-android/master/images/cheesburger_with_fries_coupon.jpeg"),
        unlocked = false,
    ),
    Coupon(
        id = ID("2"),
        name = "Chicekburger with fries",
        price = Points(150),
        image = URL("https://raw.githubusercontent.com/Maruchin1/domain-driven-android/master/images/chickenburger_with_fries_coupon.jpeg"),
        unlocked = true,
    ),
    Coupon(
        id = ID("3"),
        name = "Chicken nuggets with fries",
        price = Points(250),
        image = URL("https://raw.githubusercontent.com/Maruchin1/domain-driven-android/master/images/chicken_nuggets_with_fries_coupon.jpeg"),
        unlocked = false,
    ),
    Coupon(
        id = ID("4"),
        name = "2 x Milkshake",
        price = Points(100),
        image = URL("https://raw.githubusercontent.com/Maruchin1/domain-driven-android/master/images/two_milkshakes_coupon.jpeg"),
        unlocked = false,
    ),
    Coupon(
        id = ID("5"),
        name = "2 x Soda drink",
        price = Points(50),
        image = URL("https://raw.githubusercontent.com/Maruchin1/domain-driven-android/master/images/two_soda_drinks_coupon.jpeg"),
        unlocked = false,
    ),
)
