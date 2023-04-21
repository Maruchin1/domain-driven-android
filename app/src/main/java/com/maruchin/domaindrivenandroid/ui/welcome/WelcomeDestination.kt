package com.maruchin.domaindrivenandroid.ui.welcome

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val WELCOME_ROUTE = "welcome"

fun NavGraphBuilder.welcomeScreen(onSignUp: () -> Unit, onSignIn: () -> Unit) {
    composable(WELCOME_ROUTE) {
        WelcomeScreen(onSignUp = onSignUp, onSignIn = onSignIn)
    }
}
