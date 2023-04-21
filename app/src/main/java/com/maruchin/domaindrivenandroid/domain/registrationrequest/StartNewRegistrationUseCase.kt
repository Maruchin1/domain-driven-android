package com.maruchin.domaindrivenandroid.domain.registrationrequest

import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequest
import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequestRepository
import com.maruchin.domaindrivenandroid.data.values.Email
import javax.inject.Inject

class StartNewRegistrationUseCase @Inject constructor(
    private val registrationRequestRepository: RegistrationRequestRepository,
) {

    suspend operator fun invoke(email: Email) {
        val newRequest = RegistrationRequest(email)
        registrationRequestRepository.saveRegistrationRequest(newRequest)
    }
}
