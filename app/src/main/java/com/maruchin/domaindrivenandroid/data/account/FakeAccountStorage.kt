package com.maruchin.domaindrivenandroid.data.account

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeAccountStorage : AccountStorage {
    private val account = MutableStateFlow<Account?>(sampleAccount)

    override fun getLoggedInAccount(): Flow<Account?> {
        return account
    }

    override suspend fun saveLoggedInAccount(account: Account?) {
        this.account.emit(account)
    }
}
