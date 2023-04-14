package com.maruchin.domaindrivenandroid.data.activationCode

import com.maruchin.domaindrivenandroid.data.units.ID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivationCodesRepository @Inject constructor(
    private val activationCodesApi: ActivationCodesApi,
) {

    suspend fun getActivationCodeForCoupon(couponId: ID): ActivationCode {
        return activationCodesApi.fetchActivationCodeForCoupon(couponId).let(::ActivationCode)
    }
}
