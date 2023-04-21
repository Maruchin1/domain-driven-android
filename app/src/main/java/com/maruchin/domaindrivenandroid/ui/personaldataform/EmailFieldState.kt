package com.maruchin.domaindrivenandroid.ui.personaldataform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.maruchin.domaindrivenandroid.data.values.Email

@Stable
class EmailFieldState(default: Email?) {

    var value by mutableStateOf<String?>(default?.value)

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

    val completeEmail: Email?
        get() = value?.let(::Email)
}

@Composable
fun rememberEmailFieldState(defaultValue: Email?) = remember(defaultValue) {
    EmailFieldState(defaultValue)
}
