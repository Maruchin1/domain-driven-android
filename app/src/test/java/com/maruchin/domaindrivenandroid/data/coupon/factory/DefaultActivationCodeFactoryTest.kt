package com.maruchin.domaindrivenandroid.data.coupon.factory

import com.maruchin.domaindrivenandroid.data.coupon.ActivationCode
import org.junit.Assert.*
import org.junit.Test

class DefaultActivationCodeFactoryTest {
    private val activationCodeFactory = DefaultActivationCodeFactory()

    @Test
    fun `Create random activation code`() {
        val firstCode = activationCodeFactory.createRandomActivationCode()
        val secondCode = activationCodeFactory.createRandomActivationCode()

        assertNotEquals(firstCode, secondCode)
        assertEquals(ActivationCode.LENGTH, firstCode.value.length)
        assertEquals(ActivationCode.LENGTH, secondCode.value.length)
    }
}
