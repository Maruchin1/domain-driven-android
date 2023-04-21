package com.maruchin.domaindrivenandroid.data.registrationrequest

import org.junit.Assert.*
import org.junit.Test

class RegistrationRequestTest {

    @Test
    fun `Accept terms and conditions`() {
        var request = sampleRegistrationRequest

        request = request.acceptTermsAndConditions()

        assertTrue(request.termsAndConditionsAccepted)
    }
}
