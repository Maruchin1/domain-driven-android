package com.maruchin.domaindrivenandroid.domain.registrationrequest

import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequest
import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequestRepository
import javax.inject.Inject

class StartNewRegistrationUseCase @Inject constructor(
    private val registrationRequestRepository: RegistrationRequestRepository,
) {

    suspend operator fun invoke() {
        val newRequest = RegistrationRequest()
        registrationRequestRepository.saveRegistrationRequest(newRequest)
    }
}
