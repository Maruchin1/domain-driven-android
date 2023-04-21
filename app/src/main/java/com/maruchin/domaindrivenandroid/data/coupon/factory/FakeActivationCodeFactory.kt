package com.maruchin.domaindrivenandroid.data.coupon.factory

import com.maruchin.domaindrivenandroid.data.coupon.ActivationCode
import com.maruchin.domaindrivenandroid.data.coupon.sampleActivationCode

class FakeActivationCodeFactory : ActivationCodeFactory {
    override fun createRandomActivationCode(): ActivationCode {
        return sampleActivationCode
    }
}
