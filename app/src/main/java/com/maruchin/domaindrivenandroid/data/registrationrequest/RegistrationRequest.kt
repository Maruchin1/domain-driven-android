package com.maruchin.domaindrivenandroid.data.registrationrequest

import com.maruchin.domaindrivenandroid.data.values.Email

data class RegistrationRequest(
    val email: Email,
    val termsAndConditionsAccepted: Boolean = false,
) {

    fun acceptTermsAndConditions() = copy(termsAndConditionsAccepted = true)
}

val sampleRegistrationRequest = RegistrationRequest(
    email = Email("marcinpk.mp@gmail.com")
)

val sampleAcceptedRegistrationRequest = RegistrationRequest(
    email = Email("marcinpk.mp@gmail.com"),
    termsAndConditionsAccepted = true,
)
