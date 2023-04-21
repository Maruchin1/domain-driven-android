package com.maruchin.domaindrivenandroid.data.account.api

import javax.inject.Inject

const val DEFAULT_COLLECTED_POINTS = 170

class FakeAccountApi @Inject constructor() : AccountApi {

    override suspend fun createAccount(email: String): AccountJson {
        return AccountJson(email = email, collectedPoints = DEFAULT_COLLECTED_POINTS)
    }
}
