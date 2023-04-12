package com.maruchin.domaindrivenandroid.data.activationCode

@JvmInline
value class ActivationCode(val value: String) {
    init {
        check(value.length == LENGTH)
    }

    companion object {
        const val LENGTH = 8
    }
}
