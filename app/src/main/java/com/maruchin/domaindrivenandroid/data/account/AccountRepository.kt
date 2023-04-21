package com.maruchin.domaindrivenandroid.data.account

import com.maruchin.domaindrivenandroid.data.account.api.AccountApi
import com.maruchin.domaindrivenandroid.data.account.api.toModel
import com.maruchin.domaindrivenandroid.data.account.storage.AccountStorage
import com.maruchin.domaindrivenandroid.data.values.Email
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

    suspend fun updateLoggedInAccount(account: Account) {
        accountStorage.saveLoggedInAccount(account)
    }

    suspend fun clearLoggedInAccount() {
        accountStorage.saveLoggedInAccount(null)
    }

    suspend fun createAccount(email: Email) {
        val accountFromApi = accountApi.createAccount(email.value)
        accountStorage.saveLoggedInAccount(accountFromApi.toModel())
    }
}
