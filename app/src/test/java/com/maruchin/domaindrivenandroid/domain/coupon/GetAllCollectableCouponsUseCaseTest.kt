package com.maruchin.domaindrivenandroid.domain.coupon

import app.cash.turbine.test
import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.account.api.FakeAccountApi
import com.maruchin.domaindrivenandroid.data.account.sampleAccount
import com.maruchin.domaindrivenandroid.data.account.storage.FakeAccountStorage
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import com.maruchin.domaindrivenandroid.data.coupon.api.FakeCouponsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetAllCollectableCouponsUseCaseTest {
    private val scope = TestScope()
    private val couponsApi = FakeCouponsApi()
    private val accountApi = FakeAccountApi()
    private val accountStorage = FakeAccountStorage()
    private val accountRepository = AccountRepository(accountApi, accountStorage)
    private val couponsRepository = CouponsRepository(couponsApi, scope)
    private val getAllCollectableCouponsUseCase =
        GetAllCollectableCouponsUseCase(accountRepository, couponsRepository)

    @Before
    fun before() {
        runTest {
            accountRepository.createAccount(sampleAccount.email)
        }
    }

    @Test
    fun `Get all coupons`() = runTest(scope.testScheduler) {
        getAllCollectableCouponsUseCase().test {

            assertEquals(sampleCollectableCoupons, awaitItem())
        }
    }
}
