package com.maruchin.domaindrivenandroid.ui.completeregistration

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val COMPLETE_REGISTRATION_ROUTE = "complete-registration"

fun NavGraphBuilder.completeRegistrationScreen(
    onBack: () -> Unit,
    onCompletedSuccessfully: () -> Unit
) {
    composable(COMPLETE_REGISTRATION_ROUTE) {
        val viewModel = hiltViewModel<CompleteRegistrationViewModel>()
        val state by viewModel.uiState.collectAsState()
        CompleteRegistrationScreen(
            state = state,
            onBack = onBack,
            onComplete = viewModel::complete,
            onCompletedSuccessfully = onCompletedSuccessfully,
        )
    }
}

fun NavController.navigateToCompleteRegistration() {
    navigate(COMPLETE_REGISTRATION_ROUTE)
}
