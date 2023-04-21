package com.maruchin.domaindrivenandroid.data.account.api

import com.maruchin.domaindrivenandroid.data.account.sampleAccount
import org.junit.Assert.*
import org.junit.Test

class AccountJsonTest {

    @Test
    fun `Map json to model`() {
        val account = sampleAccountJson.toModel()

        assertEquals(sampleAccount, account)
    }
}
