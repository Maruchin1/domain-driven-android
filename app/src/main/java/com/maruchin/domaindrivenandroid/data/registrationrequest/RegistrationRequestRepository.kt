package com.maruchin.domaindrivenandroid.data.registrationrequest

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegistrationRequestRepository @Inject constructor() {
    private val registrationRequest = MutableStateFlow<RegistrationRequest?>(null)

    fun getRegistrationRequest(): Flow<RegistrationRequest?> {
        return registrationRequest
    }

    suspend fun saveRegistrationRequest(request: RegistrationRequest) {
        registrationRequest.emit(request)
    }
}
