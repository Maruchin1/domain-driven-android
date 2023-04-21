package com.maruchin.domaindrivenandroid.ui.completeregistration

import app.cash.turbine.test
import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.account.api.FakeAccountApi
import com.maruchin.domaindrivenandroid.data.account.storage.FakeAccountStorage
import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequestRepository
import com.maruchin.domaindrivenandroid.data.registrationrequest.sampleAcceptedRegistrationRequest
import com.maruchin.domaindrivenandroid.domain.registrationrequest.CompleteRegistrationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CompleteRegistrationViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private val accountApi = FakeAccountApi()
    private val accountStorage = FakeAccountStorage()
    private val registrationRequestRepository = RegistrationRequestRepository()
    private val accountRepository = AccountRepository(accountApi, accountStorage)
    private val completeRegistrationUseCase =
        CompleteRegistrationUseCase(registrationRequestRepository, accountRepository)
    private val viewModel by lazy {
        CompleteRegistrationViewModel(completeRegistrationUseCase)
    }

    @Before
    fun before() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Complete registration flow`() = runTest(dispatcher.scheduler) {
        registrationRequestRepository.saveRegistrationRequest(sampleAcceptedRegistrationRequest)

        viewModel.uiState.test {
            viewModel.complete()


            assertEquals(CompleteRegistrationUiState(), awaitItem())
            assertEquals(CompleteRegistrationUiState(completed = true), awaitItem())
        }
    }
}
