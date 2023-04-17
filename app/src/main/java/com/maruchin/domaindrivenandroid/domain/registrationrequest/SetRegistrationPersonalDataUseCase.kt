package com.maruchin.domaindrivenandroid.domain.registrationrequest

import com.maruchin.domaindrivenandroid.data.registrationrequest.PersonalData
import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequestRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SetRegistrationPersonalDataUseCase @Inject constructor(
    private val registrationRequestRepository: RegistrationRequestRepository,
) {

    suspend operator fun invoke(personalData: PersonalData) {
        var request = checkNotNull(registrationRequestRepository.getRegistrationRequest().first())
        request = request.setPersonalData(personalData)
        registrationRequestRepository.saveRegistrationRequest(request)
    }
}
