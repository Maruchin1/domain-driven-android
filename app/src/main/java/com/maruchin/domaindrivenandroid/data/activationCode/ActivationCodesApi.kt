package com.maruchin.domaindrivenandroid.data.activationCode

import com.maruchin.domaindrivenandroid.data.units.ID

interface ActivationCodesApi {

    suspend fun fetchActivationCodeForCoupon(couponId: ID): String
}
