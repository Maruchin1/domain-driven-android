package com.maruchin.domaindrivenandroid.data.account.api

interface AccountApi {

    suspend fun createAccount(email: String): AccountJson
}
