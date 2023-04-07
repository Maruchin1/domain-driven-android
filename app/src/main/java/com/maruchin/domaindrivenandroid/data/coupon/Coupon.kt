package com.maruchin.domaindrivenandroid.data.coupon

import java.util.Currency

data class Coupon(
    val id: String,
    val title: String,
    val subtitle: String,
    val value: Money,
)

val sampleForHomeCoupons = listOf(
    Coupon(
        id = "1",
        title = "Coupon for Allegro",
        subtitle = "Free delivery with Allegro Smart",
        value = Money(value = 50.0, currency = Currency.getInstance("PLN"))
    ),
    Coupon(
        id = "2",
        title = "Coupon for H&M",
        subtitle = "Fashion and quality clothes at a good price",
        value = Money(value = 100.0, currency = Currency.getInstance("PLN"))
    ),
    Coupon(
        id = "3",
        title = "Coupon for Ikea",
        subtitle = "Furniture and interior design for your home",
        value = Money(value = 100.0, currency = Currency.getInstance("PLN"))
    ),
    Coupon(
        id = "4",
        title = "Designer brands at TK Maxx",
        subtitle = "Hunt for stylish pearls and unique products",
        value = Money(value = 100.0, currency = Currency.getInstance("PLN"))
    ),
    Coupon(
        id = "5",
        title = "Coupon for Amazon.pl",
        subtitle = "Thousands of products in one place",
        value = Money(value = 94.0, currency = Currency.getInstance("PLN"))
    )
)
