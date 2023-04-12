package com.maruchin.domaindrivenandroid.data.account

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepository @Inject constructor() : Mutex by Mutex() {
    private var _account: Account? = sampleAccount

    suspend fun getLoggedInAccount(): Account? = withLock {
        delay(1_000)
        _account
    }

    suspend fun saveLoggedInAccount(account: Account?) = withLock {
        delay(1_000)
        _account = account
    }
}
