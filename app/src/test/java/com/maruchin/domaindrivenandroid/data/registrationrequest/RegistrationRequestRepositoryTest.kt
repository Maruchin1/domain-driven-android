package com.maruchin.domaindrivenandroid.data.registrationrequest

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RegistrationRequestRepositoryTest {
    private val registrationRequestRepository = RegistrationRequestRepository()

    @Test
    fun `No request by default`() = runTest {
        registrationRequestRepository.getRegistrationRequest().test {

            assertNull(awaitItem())
        }
    }

    @Test
    fun `Save registration request`() = runTest {
        registrationRequestRepository.getRegistrationRequest().test {
            registrationRequestRepository.saveRegistrationRequest(sampleRegistrationRequest)

            assertNull(awaitItem())
            assertEquals(sampleRegistrationRequest, awaitItem())
        }
    }
}
