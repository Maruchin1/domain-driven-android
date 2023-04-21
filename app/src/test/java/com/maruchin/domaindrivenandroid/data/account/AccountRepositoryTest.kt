package com.maruchin.domaindrivenandroid.data.account

import app.cash.turbine.test
import com.maruchin.domaindrivenandroid.data.account.api.FakeAccountApi
import com.maruchin.domaindrivenandroid.data.account.storage.FakeAccountStorage
import com.maruchin.domaindrivenandroid.data.coupon.sampleCoupons
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AccountRepositoryTest {
    private val accountApi = FakeAccountApi()
    private val accountStorage = FakeAccountStorage()
    private val accountRepository = AccountRepository(accountApi, accountStorage)

    @Test
    fun `No logged account by default`() = runTest {
        accountRepository.getLoggedInAccount().test {

            assertNull(awaitItem())
        }
    }

    @Test
    fun `Create account`() = runTest {
        accountRepository.getLoggedInAccount().test {
            accountRepository.createAccount(sampleAccount.email)

            assertNull(awaitItem())
            assertEquals(sampleAccount, awaitItem())
        }
    }

    @Test
    fun `Update logged in account`() = runTest {
        accountRepository.getLoggedInAccount().test {
            accountRepository.createAccount(sampleAccount.email)
            val updatedAccount = sampleAccount.exchangePointsFor(sampleCoupons[1])
            accountRepository.updateLoggedInAccount(updatedAccount)

            assertNull(awaitItem())
            assertEquals(sampleAccount, awaitItem())
            assertEquals(updatedAccount, awaitItem())
        }
    }

    @Test
    fun `Clear logged in account`() = runTest {
        accountRepository.getLoggedInAccount().test {
            accountRepository.createAccount(sampleAccount.email)
            accountRepository.clearLoggedInAccount()

            assertNull(awaitItem())
            assertEquals(sampleAccount, awaitItem())
            assertNull(awaitItem())
        }
    }
}
