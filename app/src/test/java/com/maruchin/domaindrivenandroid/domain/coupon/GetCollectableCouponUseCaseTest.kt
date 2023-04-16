package com.maruchin.domaindrivenandroid.domain.coupon

import app.cash.turbine.test
import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import com.maruchin.domaindrivenandroid.data.coupon.FakeCouponsApi
import com.maruchin.domaindrivenandroid.data.coupon.sampleCoupons
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCollectableCouponUseCaseTest {
    private val scope = TestScope()
    private val couponsApi = FakeCouponsApi()
    private val accountRepository = AccountRepository()
    private val couponsRepository = CouponsRepository(couponsApi, scope)
    private val getCollectableCouponUseCase =
        GetCollectableCouponUseCase(accountRepository, couponsRepository)

    @Test
    fun `Get coupon which can be collected`() = scope.runTest {
        val coupon = sampleCoupons[1]

        getCollectableCouponUseCase(coupon.id).test {

            assertEquals(CollectableCoupon(coupon = coupon, canCollect = true), awaitItem())
        }
    }

    @Test
    fun `Get coupon which can't be collected`() = scope.runTest {
        val coupon = sampleCoupons[0]

        getCollectableCouponUseCase(coupon.id).test {

            assertEquals(CollectableCoupon(coupon = coupon, canCollect = false), awaitItem())
        }
    }
}
