package com.maruchin.domaindrivenandroid.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.maruchin.domaindrivenandroid.ui.completeregistration.completeRegistrationScreen
import com.maruchin.domaindrivenandroid.ui.completeregistration.navigateToCompleteRegistration
import com.maruchin.domaindrivenandroid.ui.personaldataform.navigateToPersonalDataForm
import com.maruchin.domaindrivenandroid.ui.personaldataform.personalDataFormScreen
import com.maruchin.domaindrivenandroid.ui.termsandconditions.navigateToTermsAndConditions
import com.maruchin.domaindrivenandroid.ui.termsandconditions.termsAndConditionsScreen
import com.maruchin.domaindrivenandroid.ui.welcome.WELCOME_ROUTE
import com.maruchin.domaindrivenandroid.ui.welcome.welcomeScreen

const val REGISTRATION_GRAPH_ROUTE = "registration-graph"

fun NavGraphBuilder.registrationGraph(
    navController: NavController,
    onCompletedSuccessfully: () -> Unit,
) {
    navigation(startDestination = WELCOME_ROUTE, route = REGISTRATION_GRAPH_ROUTE) {
        welcomeScreen(
            onSignUp = { navController.navigateToPersonalDataForm() },
            onSignIn = {},
        )
        personalDataFormScreen(
            onBack = { navController.navigateUp() },
            onProceed = { navController.navigateToTermsAndConditions() },
        )
        termsAndConditionsScreen(
            onBack = { navController.navigateUp() },
            onContinue = { navController.navigateToCompleteRegistration() },
        )
        completeRegistrationScreen(
            onBack = { navController.navigateUp() },
            onCompletedSuccessfully = onCompletedSuccessfully,
        )
    }
}

fun NavController.navigateToRegistrationGraph() {
    navigate(REGISTRATION_GRAPH_ROUTE) {
        popUpTo(graph.findStartDestination().id) {
            inclusive = true
        }
    }
}
