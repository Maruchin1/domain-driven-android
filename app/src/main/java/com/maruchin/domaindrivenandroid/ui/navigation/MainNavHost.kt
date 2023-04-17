package com.maruchin.domaindrivenandroid.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavHost(isLoggedIn: Boolean) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) HOME_GRAPH_ROUTE else REGISTRATION_GRAPH_ROUTE,
    ) {
        registrationGraph(
            navController = navController,
            onCompletedSuccessfully = { navController.navigateToHomeGraph() },
        )
        homeGraph(
            navController = navController,
            onLoggedOut = { navController.navigateToRegistrationGraph() },
        )
    }
}
