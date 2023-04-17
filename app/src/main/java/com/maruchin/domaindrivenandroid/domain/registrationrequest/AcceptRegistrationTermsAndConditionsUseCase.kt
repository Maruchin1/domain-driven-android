package com.maruchin.domaindrivenandroid.domain.registrationrequest

import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequestRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AcceptRegistrationTermsAndConditionsUseCase @Inject constructor(
    private val registrationRequestRepository: RegistrationRequestRepository,
) {

    suspend operator fun invoke() {
        var request = checkNotNull(registrationRequestRepository.getRegistrationRequest().first())
        request = request.acceptTermsAndConditions()
        registrationRequestRepository.saveRegistrationRequest(request)
    }
}
