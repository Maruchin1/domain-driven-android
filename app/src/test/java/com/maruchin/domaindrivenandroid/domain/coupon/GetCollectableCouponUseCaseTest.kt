package com.maruchin.domaindrivenandroid.domain.coupon

import app.cash.turbine.test
import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.account.api.FakeAccountApi
import com.maruchin.domaindrivenandroid.data.account.sampleAccount
import com.maruchin.domaindrivenandroid.data.account.storage.FakeAccountStorage
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import com.maruchin.domaindrivenandroid.data.coupon.api.FakeCouponsApi
import com.maruchin.domaindrivenandroid.data.coupon.sampleCoupons
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCollectableCouponUseCaseTest {
    private val scope = TestScope()
    private val couponsApi = FakeCouponsApi()
    private val accountApi = FakeAccountApi()
    private val accountStorage = FakeAccountStorage()
    private val accountRepository = AccountRepository(accountApi, accountStorage)
    private val couponsRepository = CouponsRepository(couponsApi, scope)
    private val getCollectableCouponUseCase =
        GetCollectableCouponUseCase(accountRepository, couponsRepository)

    @Before
    fun before() {
        runTest {
            accountRepository.createAccount(sampleAccount.email)
        }
    }

    @Test
    fun `Get coupon which can be collected`() = runTest(scope.testScheduler) {
        val coupon = sampleCoupons[1]

        getCollectableCouponUseCase(coupon.id).test {

            assertEquals(CollectableCoupon(coupon = coupon, canCollect = true), awaitItem())
        }
    }

    @Test
    fun `Get coupon which can't be collected`() = runTest(scope.testScheduler) {
        val coupon = sampleCoupons[0]

        getCollectableCouponUseCase(coupon.id).test {

            assertEquals(CollectableCoupon(coupon = coupon, canCollect = false), awaitItem())
        }
    }
}
