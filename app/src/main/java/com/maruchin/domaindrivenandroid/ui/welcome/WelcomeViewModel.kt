package com.maruchin.domaindrivenandroid.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.domaindrivenandroid.domain.registrationrequest.StartNewRegistrationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val startNewRegistrationUseCase: StartNewRegistrationUseCase
) : ViewModel() {

    fun signUp() = viewModelScope.launch {
        startNewRegistrationUseCase()
    }
}
