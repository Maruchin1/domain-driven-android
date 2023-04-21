package com.maruchin.domaindrivenandroid.data.account.storage

import com.maruchin.domaindrivenandroid.data.account.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeAccountStorage : AccountStorage {
    private val account = MutableStateFlow<Account?>(null)

    override fun getLoggedInAccount(): Flow<Account?> {
        return account
    }

    override suspend fun saveLoggedInAccount(account: Account?) {
        this.account.emit(account)
    }
}
