package com.maruchin.domaindrivenandroid.ui.termsandconditions

import androidx.compose.runtime.Immutable
import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequest

@Immutable
data class TermsAndConditionsUiState(
    val registrationRequest: RegistrationRequest? = null,
)
