package com.maruchin.domaindrivenandroid.data.coupon

import com.maruchin.domaindrivenandroid.data.values.ID
import com.maruchin.domaindrivenandroid.data.values.Name
import com.maruchin.domaindrivenandroid.data.values.Points
import java.net.URL

data class Coupon(
    val id: ID,
    val name: Name,
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

val sampleCoupons = listOf(
    Coupon(
        id = ID("1"),
        name = Name("Cheesburger with fries"),
        points = Points(200),
        image = URL("https://raw.githubusercontent.com/Maruchin1/domain-driven-android/master/images/cheesburger_with_fries_coupon.jpeg"),
        activationCode = null,
    ),
    Coupon(
        id = ID("2"),
        name = Name("2 x Milkshake"),
        points = Points(100),
        image = URL("https://raw.githubusercontent.com/Maruchin1/domain-driven-android/master/images/two_milkshakes_coupon.jpeg"),
        activationCode = null,
    ),
    Coupon(
        id = ID("3"),
        name = Name("2 x Soda drink"),
        points = Points(50),
        image = URL("https://raw.githubusercontent.com/Maruchin1/domain-driven-android/master/images/two_soda_drinks_coupon.jpeg"),
        activationCode = null,
    )
)
