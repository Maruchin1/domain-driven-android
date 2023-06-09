package com.maruchin.domaindrivenandroid.ui.personaldataform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequestRepository
import com.maruchin.domaindrivenandroid.data.values.Email
import com.maruchin.domaindrivenandroid.domain.registrationrequest.StartNewRegistrationUseCase
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
class PersonalDataFormViewModel @Inject constructor(
    private val registrationRequestRepository: RegistrationRequestRepository,
    private val startNewRegistrationUseCase: StartNewRegistrationUseCase,
) : ViewModel() {

    private val request = registrationRequestRepository.getRegistrationRequest()

    val uiState: StateFlow<PersonalDataFormUiState> = request.map { registrationRequest ->
        PersonalDataFormUiState(registrationRequest = registrationRequest)
    }.catch { error ->
        logError(error)
    }.stateIn(viewModelScope, SharingStarted.Lazily, PersonalDataFormUiState())

    fun proceed(email: Email) = viewModelScope.launch {
        startNewRegistrationUseCase(email)
    }
}
