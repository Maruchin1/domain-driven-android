package com.maruchin.domaindrivenandroid.data.activationCode

import com.maruchin.domaindrivenandroid.data.coupon.sampleCoupons
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ActivationCodesRepositoryTest {
    private val activationCodesApi = FakeActivationCodesApi()
    private val activationCodesRepository = ActivationCodesRepository(activationCodesApi)

    @Test
    fun `Get activation code for coupon`() = runTest {
        val coupon = sampleCoupons[1]
        activationCodesApi.activationCode = sampleActivationCodeJson

        val activationCode = activationCodesRepository.getActivationCodeForCoupon(coupon.id)

        assertEquals(sampleActivationCode, activationCode)
    }
}
