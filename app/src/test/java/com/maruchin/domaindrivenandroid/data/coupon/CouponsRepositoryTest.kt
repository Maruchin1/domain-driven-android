package com.maruchin.domaindrivenandroid.data.coupon

import app.cash.turbine.test
import com.maruchin.domaindrivenandroid.data.coupon.api.FakeCouponsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CouponsRepositoryTest {
    private val couponsApi = FakeCouponsApi()
    private val scope = TestScope()
    private val couponsRepository = CouponsRepository(couponsApi, scope)

    @Test
    fun `Get all coupons`() = scope.runTest {
        couponsRepository.getAllCoupons().test {

            assertEquals(sampleCoupons, awaitItem())
        }
    }

    @Test
    fun `Get coupon`() = scope.runTest {
        val coupon = sampleCoupons[1]

        couponsRepository.getCoupon(coupon.id).test {

            assertEquals(coupon, awaitItem())
        }
    }

    @Test
    fun `Update coupon`() = scope.runTest {
        var coupon = sampleCoupons[1]
        val activationCode = sampleActivationCode

        couponsRepository.getCoupon(coupon.id).test {

            assertEquals(coupon, awaitItem())

            coupon = coupon.collect(activationCode)
            couponsRepository.updateCoupon(coupon)

            assertEquals(coupon, awaitItem())
        }
    }
}
