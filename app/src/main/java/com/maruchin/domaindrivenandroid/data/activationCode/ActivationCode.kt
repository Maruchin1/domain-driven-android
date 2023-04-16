package com.maruchin.domaindrivenandroid.data.activationCode

import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds


data class ActivationCode(val value: String, val remainingTime: Duration) {
    init {
        check(value.length == LENGTH)
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
