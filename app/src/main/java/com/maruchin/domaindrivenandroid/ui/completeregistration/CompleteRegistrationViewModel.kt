package com.maruchin.domaindrivenandroid.ui.completeregistration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.domaindrivenandroid.domain.registrationrequest.CompleteRegistrationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompleteRegistrationViewModel @Inject constructor(
    private val completeRegistrationUseCase: CompleteRegistrationUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CompleteRegistrationUiState())
    val uiState = _uiState.asStateFlow()

    fun complete() = viewModelScope.launch {
        completeRegistrationUseCase()
        _uiState.update { it.copy(completed = true) }
    }
}
