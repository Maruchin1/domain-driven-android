package com.maruchin.domaindrivenandroid.data.coupon

import java.text.DecimalFormat
import java.util.Currency

data class Money(val value: Double, val currency: Currency) {

    override fun toString(): String {
        val formatter = DecimalFormat.getCurrencyInstance()
        formatter.currency = currency
        return formatter.format(value)
    }
}
