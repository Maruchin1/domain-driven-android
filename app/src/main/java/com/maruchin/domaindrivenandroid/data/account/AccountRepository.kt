package com.maruchin.domaindrivenandroid.data.account

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepository @Inject constructor(
    private val accountApi: AccountApi,
    private val accountStorage: AccountStorage,
) {

    fun getLoggedInAccount(): Flow<Account?> {
        return accountStorage.getLoggedInAccount()
    }

    suspend fun saveLoggedInAccount(account: Account) {
        accountStorage.saveLoggedInAccount(account)
    }

    suspend fun clearLoggedInAccount() {
        accountStorage.saveLoggedInAccount(null)
    }

    suspend fun createAccount(username: String, email: String) {
        val accountFromApi = accountApi.createAccount(username, email)
        accountStorage.saveLoggedInAccount(accountFromApi.toModel())
    }
}
