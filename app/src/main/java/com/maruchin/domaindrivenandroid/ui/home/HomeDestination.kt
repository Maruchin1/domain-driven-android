package com.maruchin.domaindrivenandroid.ui.home

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maruchin.domaindrivenandroid.data.units.ID

const val HOME_ROUTE = "home"

fun NavGraphBuilder.homeScreen(onOpenCoupon: (ID) -> Unit, onLoggedOut: () -> Unit) {
    composable(HOME_ROUTE) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val state by viewModel.uiState.collectAsState()
        HomeScreen(
            state = state,
            onOpenCoupon = onOpenCoupon,
            onLogout = viewModel::logout,
            onLoggedOut = onLoggedOut,
        )
    }
}
