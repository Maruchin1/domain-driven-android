package com.maruchin.domaindrivenandroid.ui.personaldataform

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val PERSONAL_DATA_FORM_ROUTE = "personal-data-form"

fun NavGraphBuilder.personalDataFormScreen(onBack: () -> Unit, onProceed: () -> Unit) {
    composable(PERSONAL_DATA_FORM_ROUTE) {
        val viewModel = hiltViewModel<PersonalDataFormViewModel>()
        val state by viewModel.uiState.collectAsState()
        PersonalDataFormScreen(
            state = state,
            onBack = onBack,
            onProceed = { viewModel.proceed(it); onProceed() },
        )
    }
}

fun NavController.navigateToPersonalDataForm() {
    navigate(PERSONAL_DATA_FORM_ROUTE)
}
