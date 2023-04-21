package com.maruchin.domaindrivenandroid.ui.personaldataform

import app.cash.turbine.test
import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequestRepository
import com.maruchin.domaindrivenandroid.data.registrationrequest.sampleRegistrationRequest
import com.maruchin.domaindrivenandroid.domain.registrationrequest.StartNewRegistrationUseCase
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
class PersonalDataFormViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private val registrationRequestRepository = RegistrationRequestRepository()
    private val startNewRegistrationUseCase =
        StartNewRegistrationUseCase(registrationRequestRepository)
    private val viewModel by lazy {
        PersonalDataFormViewModel(registrationRequestRepository, startNewRegistrationUseCase)
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
    fun `Request not started`() = runTest(dispatcher.scheduler) {
        viewModel.uiState.test {

            assertEquals(PersonalDataFormUiState(), awaitItem())
        }
    }

    @Test
    fun `Request already started`() = runTest(dispatcher.scheduler) {
        registrationRequestRepository.saveRegistrationRequest(sampleRegistrationRequest)

        viewModel.uiState.test {

            assertEquals(PersonalDataFormUiState(), awaitItem())
            assertEquals(PersonalDataFormUiState(sampleRegistrationRequest), awaitItem())
        }
    }

    @Test
    fun `Proceed with request`() = runTest(dispatcher.scheduler) {
        viewModel.uiState.test {
            viewModel.proceed(sampleRegistrationRequest.email)

            assertEquals(PersonalDataFormUiState(), awaitItem())
            assertEquals(PersonalDataFormUiState(sampleRegistrationRequest), awaitItem())
        }
    }
}
