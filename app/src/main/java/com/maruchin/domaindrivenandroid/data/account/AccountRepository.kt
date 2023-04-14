package com.maruchin.domaindrivenandroid.data.account

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepository @Inject constructor() {
    private val account = MutableStateFlow<Account?>(sampleAccount)

    fun getLoggedInAccount(): Flow<Account?> {
        return account
    }

    suspend fun saveLoggedInAccount(account: Account?) {
        this.account.emit(account)
    }
}
