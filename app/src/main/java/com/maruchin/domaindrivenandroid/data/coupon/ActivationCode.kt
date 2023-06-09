package com.maruchin.domaindrivenandroid.data.coupon

import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds


data class ActivationCode(val value: String, val remainingTime: Duration) {
    init {
        require(value.length == LENGTH)
    }

    val expired: Boolean
        get() = remainingTime.inWholeSeconds <= 0

    suspend fun waitForActivation(): ActivationCode {
        check(!expired)
        delay(1.seconds)
        return copy(remainingTime = remainingTime - 1.seconds)
    }

    companion object {
        const val LENGTH = 8
    }
}

val sampleActivationCode = ActivationCode(
    value = "QW123456",
    remainingTime = 60.seconds,
)
