package com.maruchin.domaindrivenandroid.data.coupon.factory

import com.maruchin.domaindrivenandroid.data.coupon.ActivationCode
import javax.inject.Inject
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

const val ALLOWED_CHARS = "1234567890QWERTYUIOPASDFGHJKLZXCVBNM"
const val REMAINING_SECONDS = 60

class DefaultActivationCodeFactory @Inject constructor() : ActivationCodeFactory {

    override fun createRandomActivationCode(): ActivationCode {
        val code = buildString(ActivationCode.LENGTH) {
            repeat(ActivationCode.LENGTH) {
                append(ALLOWED_CHARS[Random.nextInt(ALLOWED_CHARS.length)])
            }
        }
        return ActivationCode(value = code, remainingTime = REMAINING_SECONDS.seconds)
    }
}
