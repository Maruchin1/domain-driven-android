package com.maruchin.domaindrivenandroid.ui.personaldataform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Stable
class EmailFieldState(defaultValue: String?) {

    var value by mutableStateOf<String?>(defaultValue)

    val error: String?
        get() = value.let {
            when {
                it == null -> null
                it.isBlank() -> "Please enter your email address"
                else -> null
            }
        }

    val isValid: Boolean
        get() = value != null && error == null
}

@Composable
fun rememberEmailFieldState(defaultValue: String?) = remember(defaultValue) {
    EmailFieldState(defaultValue)
}
