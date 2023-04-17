package com.maruchin.domaindrivenandroid.data.account

interface AccountApi {

    suspend fun createAccount(username: String, email: String): AccountJson
}
