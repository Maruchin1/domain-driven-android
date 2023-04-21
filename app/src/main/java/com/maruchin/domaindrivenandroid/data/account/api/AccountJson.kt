package com.maruchin.domaindrivenandroid.data.account.api

import com.maruchin.domaindrivenandroid.data.account.Account
import com.maruchin.domaindrivenandroid.data.values.Email
import com.maruchin.domaindrivenandroid.data.values.Points

data class AccountJson(val email: String, val collectedPoints: Int)

fun AccountJson.toModel() = Account(
    email = Email(email),
    collectedPoints = Points(collectedPoints),
)

val sampleAccountJson = AccountJson(
    email = "marcinpk.mp@gmail.com",
    collectedPoints = 170,
)
