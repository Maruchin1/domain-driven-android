package com.maruchin.domaindrivenandroid.domain.coupon

import app.cash.turbine.test
import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.account.api.FakeAccountApi
import com.maruchin.domaindrivenandroid.data.account.storage.FakeAccountStorage
import com.maruchin.domaindrivenandroid.data.account.sampleAccount
import com.maruchin.domaindrivenandroid.data.coupon.sampleActivationCode
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import com.maruchin.domaindrivenandroid.data.coupon.api.FakeCouponsApi
import com.maruchin.domaindrivenandroid.data.coupon.sampleCoupons
import com.maruchin.domaindrivenandroid.data.coupon.factory.FakeActivationCodeFactory
import com.maruchin.domaindrivenandroid.data.values.Points
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
class CollectCouponUseCaseTest {
    private val scope = TestScope()
    private val couponsApi = FakeCouponsApi()
    private val accountApi = FakeAccountApi()
    private val accountStorage = FakeAccountStorage()
    private val accountRepository = AccountRepository(accountApi, accountStorage)
    private val couponsRepository = CouponsRepository(couponsApi, scope)
    private val activationCodeFactory = FakeActivationCodeFactory()
    private val collectCouponUseCase =
        CollectCouponUseCase(accountRepository, couponsRepository, activationCodeFactory, scope)

    @Before
    fun before() {
        runTest {
            accountRepository.createAccount(sampleAccount.email)
        }
    }

    @Test
    fun `Update account when can pay for coupon`() = runTest(scope.testScheduler) {
        val coupon = sampleCoupons[2]

        accountRepository.getLoggedInAccount().test {
            collectCouponUseCase(coupon.id)


            assertEquals(sampleAccount, awaitItem())
            assertEquals(Points(120), awaitItem()!!.collectedPoints)
        }
    }

    @Test
    fun `Update coupon when can pay for coupon`() = runTest(scope.testScheduler) {
        val coupon = sampleCoupons[2]

        couponsRepository.getCoupon(coupon.id).test {
            collectCouponUseCase(coupon.id)

            assertEquals(coupon, awaitItem())
            assertEquals(sampleActivationCode, awaitItem()!!.activationCode)
        }
    }

    @Test
    fun `Reset coupon when collected and expired`() = runTest(scope.testScheduler) {
        val coupon = sampleCoupons[2]

        couponsRepository.getCoupon(coupon.id).test {
            collectCouponUseCase(coupon.id)

            advanceTimeBy(10_001)
            assertEquals(50.seconds, expectMostRecentItem()!!.activationCode!!.remainingTime)

            advanceTimeBy(10_001)
            assertEquals(40.seconds, expectMostRecentItem()!!.activationCode!!.remainingTime)

            advanceTimeBy(40_001)
            assertNull(expectMostRecentItem()!!.activationCode)
        }
    }
}
