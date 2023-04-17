package com.maruchin.domaindrivenandroid.data.account

import kotlinx.coroutines.flow.Flow

interface AccountStorage {

    fun getLoggedInAccount(): Flow<Account?>

    suspend fun saveLoggedInAccount(account: Account?)
}
