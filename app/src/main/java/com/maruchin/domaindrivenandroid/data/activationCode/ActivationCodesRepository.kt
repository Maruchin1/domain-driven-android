package com.maruchin.domaindrivenandroid.data.activationCode

import com.maruchin.domaindrivenandroid.data.ID
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

const val ALLOWED_CHARS = "1234567890QWERTYUIOPASDFGHJKLZXCVBNM"

@Singleton
class ActivationCodesRepository @Inject constructor() {

    suspend fun getActivationCodeForCoupon(couponId: ID): ActivationCode {
        delay(2_000)
        val code = buildString(ActivationCode.LENGTH) {
            repeat(ActivationCode.LENGTH) {
                append(ALLOWED_CHARS[Random.nextInt(ALLOWED_CHARS.length)])
            }
        }
        return ActivationCode(code)
    }
}
