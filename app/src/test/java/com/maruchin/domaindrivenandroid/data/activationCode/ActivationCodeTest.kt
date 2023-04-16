package com.maruchin.domaindrivenandroid.data.activationCode

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
class ActivationCodeTest {

    @Test
    fun `Create valid activation code`() {
        ActivationCode(
            value = "12345678",
            remainingTime = 60.seconds,
        )
    }

    @Test
    fun `Create invalid activation code`() {
        assertThrows(IllegalStateException::class.java) {
            ActivationCode(
                value = "1234567",
                remainingTime = 60.seconds,
            )
        }
    }

    @Test
    fun `Countdown until expired`() = runTest {
        var activationCode = sampleActivationCode

        launch {
            while (!activationCode.expired) {
                activationCode = activationCode.waitForActivation()
            }
        }

        assertFalse(activationCode.expired)
        advanceTimeBy(61_000)
        assertTrue(activationCode.expired)
    }
}
