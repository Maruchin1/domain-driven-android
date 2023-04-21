package com.maruchin.domaindrivenandroid.ui.main

import app.cash.turbine.test
import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.account.api.FakeAccountApi
import com.maruchin.domaindrivenandroid.data.account.sampleAccount
import com.maruchin.domaindrivenandroid.data.account.storage.FakeAccountStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private val accountApi = FakeAccountApi()
    private val accountStorage = FakeAccountStorage()
    private val accountRepository = AccountRepository(accountApi, accountStorage)
    private val viewModel by lazy { MainViewModel(accountRepository) }

    @Before
    fun before() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `User is not logged in`() = runTest(dispatcher.scheduler) {
        viewModel.isLoggedIn.test {

            assertNull(awaitItem())
            assertFalse(awaitItem()!!)
        }
    }

    @Test
    fun `User is logged in`() = runTest(dispatcher.scheduler) {
        accountRepository.createAccount(sampleAccount.email)

        viewModel.isLoggedIn.test {

            assertNull(awaitItem())
            assertTrue(awaitItem()!!)
        }
    }
}
