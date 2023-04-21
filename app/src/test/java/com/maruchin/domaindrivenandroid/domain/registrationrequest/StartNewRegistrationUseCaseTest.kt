package com.maruchin.domaindrivenandroid.domain.registrationrequest

import com.maruchin.domaindrivenandroid.data.account.sampleAccount
import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequestRepository
import com.maruchin.domaindrivenandroid.data.registrationrequest.sampleAcceptedRegistrationRequest
import com.maruchin.domaindrivenandroid.data.registrationrequest.sampleRegistrationRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StartNewRegistrationUseCaseTest {
    private val registrationRequestRepository = RegistrationRequestRepository()
    private val startNewRegistrationUseCase =
        StartNewRegistrationUseCase(registrationRequestRepository)

    @Test
    fun `No request other request started`() = runTest {
        startNewRegistrationUseCase(sampleAccount.email)
        val request = registrationRequestRepository.getRegistrationRequest().first()

        assertEquals(sampleRegistrationRequest, request)
    }

    @Test
    fun `Replace other started request`() = runTest {
        registrationRequestRepository.saveRegistrationRequest(sampleAcceptedRegistrationRequest)

        startNewRegistrationUseCase(sampleAccount.email)
        val request = registrationRequestRepository.getRegistrationRequest().first()

        assertEquals(sampleRegistrationRequest, request)
    }
}
