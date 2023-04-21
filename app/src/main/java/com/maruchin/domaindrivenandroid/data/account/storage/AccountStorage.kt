package com.maruchin.domaindrivenandroid.data.account.storage

import com.maruchin.domaindrivenandroid.data.account.Account
import kotlinx.coroutines.flow.Flow

interface AccountStorage {

    fun getLoggedInAccount(): Flow<Account?>

    suspend fun saveLoggedInAccount(account: Account?)
}
