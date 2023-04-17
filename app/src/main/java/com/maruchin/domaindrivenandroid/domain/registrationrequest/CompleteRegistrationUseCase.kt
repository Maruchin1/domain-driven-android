package com.maruchin.domaindrivenandroid.domain.registrationrequest

import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.registrationrequest.RegistrationRequestRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CompleteRegistrationUseCase @Inject constructor(
    private val registrationRequestRepository: RegistrationRequestRepository,
    private val accountRepository: AccountRepository,
) {

    suspend operator fun invoke() {
        val request = checkNotNull(registrationRequestRepository.getRegistrationRequest().first())
        check(request.termsAndConditionsAccepted)
        val personalData = checkNotNull(request.personalData)
        accountRepository.createAccount(
            username = personalData.username,
            email = personalData.email,
        )
    }
}
