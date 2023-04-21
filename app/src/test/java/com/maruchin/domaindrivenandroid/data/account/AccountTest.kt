package com.maruchin.domaindrivenandroid.data.account

import com.maruchin.domaindrivenandroid.data.coupon.sampleCoupons
import com.maruchin.domaindrivenandroid.data.values.Points
import org.junit.Assert.*
import org.junit.Test

class AccountTest {

    @Test
    fun `Can exchange points for coupon when has enough points`() {
        var account = sampleAccount
        val coupon = sampleCoupons[2]

        val canPay = account.canExchangePointsFor(coupon)
        account = account.exchangePointsFor(coupon)

        assertTrue(canPay)
        assertEquals(Points(120), account.collectedPoints)
    }

    @Test
    fun `Can't exchange points for coupon when has less points than needed`() {
        var account = sampleAccount
        val coupon = sampleCoupons[0]

        val canPay = account.canExchangePointsFor(coupon)
        val result = runCatching {
            account = account.exchangePointsFor(coupon)
        }

        assertFalse(canPay)
        assertTrue(result.isFailure)
    }
}
