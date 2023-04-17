package com.maruchin.domaindrivenandroid.data.registrationrequest

data class RegistrationRequest(
    val personalData: PersonalData? = null,
    val termsAndConditionsAccepted: Boolean = false,
) {

    fun setPersonalData(personalData: PersonalData) = copy(
        personalData = personalData,
    )

    fun acceptTermsAndConditions() = copy(
        termsAndConditionsAccepted = true,
    )
}
