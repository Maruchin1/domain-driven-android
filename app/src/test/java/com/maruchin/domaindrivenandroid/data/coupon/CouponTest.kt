package com.maruchin.domaindrivenandroid.data.coupon

import com.maruchin.domaindrivenandroid.data.activationCode.sampleActivationCode
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CouponTest {

    @Test
    fun `Not collected coupon`() {
        val coupon = sampleCoupons[0]

        assertFalse(coupon.canActivate)
        assertNull(coupon.activationCode)
    }

    @Test
    fun `Collect coupon`() {
        var coupon = sampleCoupons[0]
        val activationCode = sampleActivationCode

        coupon = coupon.collect(activationCode)

        assertTrue(coupon.canActivate)
        assertEquals(sampleActivationCode, coupon.activationCode)
    }

    @Test
    fun `Wait until coupon expired`() = runTest {
        var coupon = sampleCoupons[0]
        val activationCode = sampleActivationCode

        coupon = coupon.collect(activationCode)
        launch {
            while (coupon.canActivate) {
                coupon = coupon.waitForActivation()
            }
        }

        assertTrue(coupon.canActivate)
        advanceTimeBy(61_000)
        assertFalse(coupon.canActivate)
    }

    @Test
    fun `Reset coupon`() {
        var coupon = sampleCoupons[0]
        val activationCode = sampleActivationCode

        coupon = coupon.collect(activationCode)
        coupon = coupon.reset()

        assertFalse(coupon.canActivate)
        assertNull(coupon.activationCode)
    }
}
