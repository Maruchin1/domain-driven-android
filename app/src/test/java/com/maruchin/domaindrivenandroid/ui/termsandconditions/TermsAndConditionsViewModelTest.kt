package com.maruchin.domaindrivenandroid.ui.termsandconditions

import app.cash.turbine.test
import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequestRepository
import com.maruchin.domaindrivenandroid.data.registrationrequest.sampleAcceptedRegistrationRequest
import com.maruchin.domaindrivenandroid.data.registrationrequest.sampleRegistrationRequest
import com.maruchin.domaindrivenandroid.domain.registrationrequest.AcceptRegistrationTermsAndConditionsUseCase
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
class TermsAndConditionsViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private val registrationRequestRepository = RegistrationRequestRepository()
    private val acceptRegistrationTermsAndConditionsUseCase =
        AcceptRegistrationTermsAndConditionsUseCase(registrationRequestRepository)
    private val viewModel by lazy {
        TermsAndConditionsViewModel(
            registrationRequestRepository,
            acceptRegistrationTermsAndConditionsUseCase
        )
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
    fun `Request only started`() = runTest(dispatcher.scheduler) {
        registrationRequestRepository.saveRegistrationRequest(sampleRegistrationRequest)

        viewModel.uiState.test {

            assertEquals(TermsAndConditionsUiState(), awaitItem())
            assertEquals(TermsAndConditionsUiState(sampleRegistrationRequest), awaitItem())
        }
    }

    @Test
    fun `Request already accepted`() = runTest(dispatcher.scheduler) {
        registrationRequestRepository.saveRegistrationRequest(sampleAcceptedRegistrationRequest)

        viewModel.uiState.test {

            assertEquals(TermsAndConditionsUiState(), awaitItem())
            assertEquals(TermsAndConditionsUiState(sampleAcceptedRegistrationRequest), awaitItem())
        }
    }

    @Test
    fun `Proceed with request`() = runTest(dispatcher.scheduler) {
        registrationRequestRepository.saveRegistrationRequest(sampleRegistrationRequest)

        viewModel.uiState.test {
            viewModel.proceed()

            assertEquals(TermsAndConditionsUiState(), awaitItem())
            assertEquals(TermsAndConditionsUiState(sampleRegistrationRequest), awaitItem())
            assertEquals(TermsAndConditionsUiState(sampleAcceptedRegistrationRequest), awaitItem())
        }
    }
}
