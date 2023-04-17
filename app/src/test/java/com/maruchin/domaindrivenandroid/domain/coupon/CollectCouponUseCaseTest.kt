package com.maruchin.domaindrivenandroid.domain.coupon

import app.cash.turbine.test
import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.account.FakeAccountApi
import com.maruchin.domaindrivenandroid.data.account.FakeAccountStorage
import com.maruchin.domaindrivenandroid.data.account.sampleAccount
import com.maruchin.domaindrivenandroid.data.activationCode.ActivationCodesRepository
import com.maruchin.domaindrivenandroid.data.activationCode.FakeActivationCodesApi
import com.maruchin.domaindrivenandroid.data.activationCode.sampleActivationCode
import com.maruchin.domaindrivenandroid.data.activationCode.sampleActivationCodeJson
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import com.maruchin.domaindrivenandroid.data.coupon.FakeCouponsApi
import com.maruchin.domaindrivenandroid.data.coupon.sampleCoupons
import com.maruchin.domaindrivenandroid.data.units.Points
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
class CollectCouponUseCaseTest {
    private val scope = TestScope()
    private val couponsApi = FakeCouponsApi()
    private val activationCodesApi = FakeActivationCodesApi()
    private val accountApi = FakeAccountApi()
    private val accountStorage = FakeAccountStorage()
    private val accountRepository = AccountRepository(accountApi, accountStorage)
    private val couponsRepository = CouponsRepository(couponsApi, scope)
    private val activationCodesRepository = ActivationCodesRepository(activationCodesApi)
    private val collectCouponUseCase =
        CollectCouponUseCase(accountRepository, couponsRepository, activationCodesRepository, scope)

    @Test
    fun `Update account when can pay for coupon`() = scope.runTest {
        val coupon = sampleCoupons[2]

        accountRepository.getLoggedInAccount().test {
            collectCouponUseCase(coupon.id)


            assertEquals(sampleAccount, awaitItem())
            assertEquals(Points(50), awaitItem()!!.collectedPoints)
        }
    }

    @Test
    fun `Update coupon when can pay for coupon`() = scope.runTest {
        activationCodesApi.activationCode = sampleActivationCodeJson
        val coupon = sampleCoupons[2]

        couponsRepository.getCoupon(coupon.id).test {
            collectCouponUseCase(coupon.id)

            assertEquals(coupon, awaitItem())
            assertEquals(sampleActivationCode, awaitItem()!!.activationCode)
        }
    }

    @Test
    fun `Reset coupon when collected and expired`() = scope.runTest {
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
