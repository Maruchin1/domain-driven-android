package com.maruchin.domaindrivenandroid.domain.registrationrequest

import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.account.api.FakeAccountApi
import com.maruchin.domaindrivenandroid.data.account.sampleAccount
import com.maruchin.domaindrivenandroid.data.account.storage.FakeAccountStorage
import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequestRepository
import com.maruchin.domaindrivenandroid.data.registrationrequest.sampleAcceptedRegistrationRequest
import com.maruchin.domaindrivenandroid.data.registrationrequest.sampleRegistrationRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CompleteRegistrationUseCaseTest {
    private val accountApi = FakeAccountApi()
    private val accountStorage = FakeAccountStorage()
    private val registrationRequestRepository = RegistrationRequestRepository()
    private val accountRepository = AccountRepository(accountApi, accountStorage)
    private val completeRegistrationRequestRepository =
        CompleteRegistrationUseCase(registrationRequestRepository, accountRepository)

    @Test
    fun `Request started and accepted`() = runTest {
        registrationRequestRepository.saveRegistrationRequest(sampleAcceptedRegistrationRequest)

        completeRegistrationRequestRepository()
        val account = accountRepository.getLoggedInAccount().first()

        assertEquals(sampleAccount, account)
    }

    @Test
    fun `Request started but not accepted`() = runTest {
        registrationRequestRepository.saveRegistrationRequest(sampleRegistrationRequest)

        val result = runCatching {
            completeRegistrationRequestRepository()
        }

        assertTrue(result.exceptionOrNull() is IllegalStateException)
    }

    @Test
    fun `Request not started`() = runTest {
        val result = runCatching {
            completeRegistrationRequestRepository()
        }

        assertTrue(result.exceptionOrNull() is IllegalStateException)
    }
}
