package com.maruchin.domaindrivenandroid.data.activationCode

import com.maruchin.domaindrivenandroid.data.units.ID
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

const val ALLOWED_CHARS = "1234567890QWERTYUIOPASDFGHJKLZXCVBNM"
const val TIME_TO_ACTIVATE = 60_000L

class FakeActivationCodesApi @Inject constructor() : ActivationCodesApi {

    override suspend fun fetchActivationCodeForCoupon(couponId: ID): ActivationCodeJson {
        delay(2_000)
        val code = buildString(ActivationCode.LENGTH) {
            repeat(ActivationCode.LENGTH) {
                append(ALLOWED_CHARS[Random.nextInt(ALLOWED_CHARS.length)])
            }
        }
        return ActivationCodeJson(value = code, timeToActivate = TIME_TO_ACTIVATE)
    }
}
