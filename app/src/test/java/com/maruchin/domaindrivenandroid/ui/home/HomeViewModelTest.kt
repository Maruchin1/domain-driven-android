package com.maruchin.domaindrivenandroid.ui.home

import app.cash.turbine.test
import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.account.api.FakeAccountApi
import com.maruchin.domaindrivenandroid.data.account.storage.FakeAccountStorage
import com.maruchin.domaindrivenandroid.data.account.sampleAccount
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import com.maruchin.domaindrivenandroid.data.coupon.api.FakeCouponsApi
import com.maruchin.domaindrivenandroid.domain.coupon.GetAllCollectableCouponsUseCase
import com.maruchin.domaindrivenandroid.domain.coupon.sampleCollectableCoupons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)
    private val couponsApi = FakeCouponsApi()
    private val accountApi = FakeAccountApi()
    private val accountStorage = FakeAccountStorage()
    private val accountRepository = AccountRepository(accountApi, accountStorage)
    private val couponsRepository = CouponsRepository(couponsApi, scope)
    private val getAllCollectableCouponsUseCase =
        GetAllCollectableCouponsUseCase(accountRepository, couponsRepository)
    private val viewModel by lazy {
        HomeViewModel(getAllCollectableCouponsUseCase, accountRepository)
    }

    @Before
    fun before() {
        Dispatchers.setMain(dispatcher)
        runTest {
            accountRepository.createAccount(sampleAccount.email)
        }
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Emit coupons`() = runTest(scope.testScheduler) {
        viewModel.uiState.test {

            assertEquals(HomeUiState(), awaitItem())
            assertEquals(
                HomeUiState(
                    myPoints = sampleAccount.collectedPoints,
                    coupons = sampleCollectableCoupons,
                    isLoading = false,
                    failedToLoadCoupons = false,
                ),
                awaitItem()
            )
        }
    }
}
