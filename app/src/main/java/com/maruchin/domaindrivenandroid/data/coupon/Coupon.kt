package com.maruchin.domaindrivenandroid.data.coupon

import com.maruchin.domaindrivenandroid.data.activationCode.ActivationCode
import com.maruchin.domaindrivenandroid.data.units.ID
import com.maruchin.domaindrivenandroid.data.units.Points
import java.net.URL

data class Coupon(
    val id: ID,
    val name: String,
    val points: Points,
    val image: URL,
    val activationCode: ActivationCode?,
) {

    val canActivate: Boolean
        get() = activationCode != null && !activationCode.expired

    fun collect(activationCode: ActivationCode) = copy(
        activationCode = activationCode
    )

    suspend fun waitForActivation() = copy(
        activationCode = activationCode?.waitForActivation()
    )

    fun reset() = copy(
        activationCode = null,
    )
}
