package com.maruchin.domaindrivenandroid.data.account

import javax.inject.Inject

class FakeAccountApi @Inject constructor() : AccountApi {

    override suspend fun createAccount(username: String, email: String): AccountJson {
        return AccountJson(username = username, email = email, collectedPoints = 170)
    }
}
