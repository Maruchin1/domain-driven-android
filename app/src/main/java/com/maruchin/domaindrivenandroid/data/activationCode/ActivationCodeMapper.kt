package com.maruchin.domaindrivenandroid.data.activationCode

import kotlin.time.Duration.Companion.milliseconds

fun ActivationCodeJson.toModel() = ActivationCode(
    value = value,
    remainingTime = timeToActivate.milliseconds,
)
