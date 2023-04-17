package com.maruchin.domaindrivenandroid.ui.welcome

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val WELCOME_ROUTE = "welcome"

fun NavGraphBuilder.welcomeScreen(onSignUp: () -> Unit, onSignIn: () -> Unit) {
    composable(WELCOME_ROUTE) {
        val viewModel = hiltViewModel<WelcomeViewModel>()
        WelcomeScreen(
            onSignUp = { viewModel.signUp(); onSignUp() },
            onSignIn = { onSignIn() }
        )
    }
}
