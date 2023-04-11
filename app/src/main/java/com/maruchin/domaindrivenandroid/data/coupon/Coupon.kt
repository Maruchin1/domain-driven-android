package com.maruchin.domaindrivenandroid.data.coupon

import com.maruchin.domaindrivenandroid.data.ID
import java.net.URL
import java.util.Currency

data class Coupon(
    val id: ID,
    val name: String,
    val price: Money,
    val image: URL,
)

val sampleCoupons = listOf(
    Coupon(
        id = ID("1"),
        name = "Cheesburger with fries",
        price = Money(value = 17.99, currency = Currency.getInstance("USD")),
        image = URL("https://raw.githubusercontent.com/Maruchin1/domain-driven-android/master/images/cheesburger_with_fries_coupon.jpeg"),
    ),
    Coupon(
        id = ID("2"),
        name = "Chicekburger with fries",
        price = Money(value = 15.99, currency = Currency.getInstance("USD")),
        image = URL("https://raw.githubusercontent.com/Maruchin1/domain-driven-android/master/images/chickenburger_with_fries_coupon.jpeg"),
    ),
    Coupon(
        id = ID("3"),
        name = "Chicken nuggets with fries",
        price = Money(value = 20.99, currency = Currency.getInstance("USD")),
        image = URL("https://raw.githubusercontent.com/Maruchin1/domain-driven-android/master/images/chicken_nuggets_with_fries_coupon.jpeg"),
    ),
    Coupon(
        id = ID("4"),
        name = "2 x Milkshake",
        price = Money(value = 8.99, currency = Currency.getInstance("USD")),
        image = URL("https://raw.githubusercontent.com/Maruchin1/domain-driven-android/master/images/two_milkshakes_coupon.jpeg"),
    ),
    Coupon(
        id = ID("5"),
        name = "2 x Soda drink",
        price = Money(value = 6.99, currency = Currency.getInstance("USD")),
        image = URL("https://raw.githubusercontent.com/Maruchin1/domain-driven-android/master/images/two_soda_drinks_coupon.jpeg"),
    ),
)
