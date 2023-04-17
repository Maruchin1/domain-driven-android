package com.maruchin.domaindrivenandroid.domain.coupon

import app.cash.turbine.test
import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.account.FakeAccountApi
import com.maruchin.domaindrivenandroid.data.account.FakeAccountStorage
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import com.maruchin.domaindrivenandroid.data.coupon.FakeCouponsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
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

    @Test
    fun `Get all coupons`() = scope.runTest {
        getAllCollectableCouponsUseCase().test {

            assertEquals(sampleCollectableCoupons, awaitItem())
        }
    }
}
