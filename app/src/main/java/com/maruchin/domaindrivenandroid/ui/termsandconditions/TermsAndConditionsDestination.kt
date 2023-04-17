package com.maruchin.domaindrivenandroid.ui.termsandconditions

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val TERMS_AND_CONDITIONS_ROUTE = "terms-and-conditions"

fun NavGraphBuilder.termsAndConditionsScreen(onBack: () -> Unit, onContinue: () -> Unit) {
    composable(TERMS_AND_CONDITIONS_ROUTE) {
        val viewModel = hiltViewModel<TermsAndConditionsViewModel>()
        val state by viewModel.uiState.collectAsState()
        TermsAndConditionsScreen(
            state = state,
            onBack = onBack,
            onContinue = { viewModel.proceed(); onContinue() },
        )
    }
}

fun NavController.navigateToTermsAndConditions() {
    navigate(TERMS_AND_CONDITIONS_ROUTE)
}
