package com.maruchin.domaindrivenandroid.data.activationCode

data class ActivationCodeJson(val value: String, val timeToActivate: Long)

val sampleActivationCodeJson = ActivationCodeJson(
    value = "QW123456",
    timeToActivate = 60_000,
)
