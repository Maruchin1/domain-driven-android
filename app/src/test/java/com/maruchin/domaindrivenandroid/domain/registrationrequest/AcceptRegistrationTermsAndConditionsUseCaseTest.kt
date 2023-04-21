package com.maruchin.domaindrivenandroid.domain.registrationrequest

import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequestRepository
import com.maruchin.domaindrivenandroid.data.registrationrequest.sampleAcceptedRegistrationRequest
import com.maruchin.domaindrivenandroid.data.registrationrequest.sampleRegistrationRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AcceptRegistrationTermsAndConditionsUseCaseTest {
    private val registrationRequestRepository = RegistrationRequestRepository()
    private val acceptRegistrationTermsAndConditionsUseCase =
        AcceptRegistrationTermsAndConditionsUseCase(registrationRequestRepository)

    @Test
    fun `Request was started`() = runTest {
        registrationRequestRepository.saveRegistrationRequest(sampleRegistrationRequest)

        acceptRegistrationTermsAndConditionsUseCase()
        val request = registrationRequestRepository.getRegistrationRequest().first()

        assertEquals(sampleAcceptedRegistrationRequest, request)
    }

    @Test
    fun `Request was not started`() {
        assertThrows(IllegalStateException::class.java) {
            runTest {
                acceptRegistrationTermsAndConditionsUseCase()
            }
        }
    }
}
