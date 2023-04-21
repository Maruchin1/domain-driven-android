package com.maruchin.domaindrivenandroid.data.coupon.factory

import com.maruchin.domaindrivenandroid.data.coupon.ActivationCode

interface ActivationCodeFactory {

    fun createRandomActivationCode(): ActivationCode
}
