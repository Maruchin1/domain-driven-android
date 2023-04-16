package com.maruchin.domaindrivenandroid.data.account

import com.maruchin.domaindrivenandroid.data.coupon.sampleCoupons
import com.maruchin.domaindrivenandroid.data.units.Points
import org.junit.Assert.*
import org.junit.Test

class AccountTest {

    @Test
    fun `Can pay for coupon when has more points`() {
        var account = sampleAccount
        val coupon = sampleCoupons[2]

        val canPay = account.canPayFor(coupon)
        account = account.payFor(coupon)

        assertTrue(canPay)
        assertEquals(Points(50), account.collectedPoints)
    }

    @Test
    fun `Can pay for coupon when has equal amount of points`() {
        var account = sampleAccount
        val coupon = sampleCoupons[1]

        val canPay = account.canPayFor(coupon)
        account = account.payFor(coupon)

        assertTrue(canPay)
        assertEquals(Points(0), account.collectedPoints)
    }

    @Test
    fun `Can't pay for coupon when less points than needed`() {
        var account = sampleAccount
        val coupon = sampleCoupons[0]

        val canPay = account.canPayFor(coupon)
        val result = runCatching {
            account = account.payFor(coupon)
        }

        assertFalse(canPay)
        assertTrue(result.isFailure)
    }
}
