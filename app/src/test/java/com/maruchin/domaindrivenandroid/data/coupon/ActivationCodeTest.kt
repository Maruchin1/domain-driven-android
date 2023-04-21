package com.maruchin.domaindrivenandroid.data.coupon

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
class ActivationCodeTest {

    @Test
    fun `Activation code with correct length`() {
        ActivationCode(value = "12345678", remainingTime = 60.seconds)
    }

    @Test
    fun `Activation code with incorrect length`() {
        assertThrows(IllegalArgumentException::class.java) {
            ActivationCode(value = "1234567", remainingTime = 60.seconds)
        }
    }

    @Test
    fun `Wait for activation`() = runTest {
        var activationCode = sampleActivationCode

        launch {
            while (!activationCode.expired) {
                activationCode = activationCode.waitForActivation()
            }
        }

        assertFalse(activationCode.expired)
        advanceTimeBy(61_000)
        assertTrue(activationCode.expired)
        assertEquals(0.seconds, activationCode.remainingTime)
    }
}
