package com.maruchin.domaindrivenandroid.data.account

import com.maruchin.domaindrivenandroid.data.units.Points

fun AccountJson.toModel() = Account(
    username = username,
    email = email,
    collectedPoints = Points(collectedPoints),
)
