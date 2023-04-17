package com.maruchin.domaindrivenandroid.ui.personaldataform

import androidx.compose.runtime.Immutable
import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequest

@Immutable
data class PersonalDataFormUiState(
    val registrationRequest: RegistrationRequest? = null,
)
