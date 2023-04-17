package com.maruchin.domaindrivenandroid.ui.termsandconditions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequestRepository
import com.maruchin.domaindrivenandroid.domain.registrationrequest.AcceptRegistrationTermsAndConditionsUseCase
import com.maruchin.domaindrivenandroid.ui.logError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TermsAndConditionsViewModel @Inject constructor(
    private val registrationRequestRepository: RegistrationRequestRepository,
    private val acceptRegistrationTermsAndConditionsUseCase: AcceptRegistrationTermsAndConditionsUseCase,
) : ViewModel() {

    private val request = registrationRequestRepository.getRegistrationRequest()

    val uiState: StateFlow<TermsAndConditionsUiState> = request.map { request ->
        TermsAndConditionsUiState(registrationRequest = request)
    }.catch { error ->
        logError(error)
    }.stateIn(viewModelScope, SharingStarted.Lazily, TermsAndConditionsUiState())

    fun proceed() = viewModelScope.launch {
        acceptRegistrationTermsAndConditionsUseCase()
    }
}
