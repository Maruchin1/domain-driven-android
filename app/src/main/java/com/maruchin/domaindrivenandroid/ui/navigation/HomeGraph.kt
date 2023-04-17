package com.maruchin.domaindrivenandroid.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.maruchin.domaindrivenandroid.ui.couponPreview.couponPreviewScreen
import com.maruchin.domaindrivenandroid.ui.couponPreview.navigateToCouponPreview
import com.maruchin.domaindrivenandroid.ui.home.HOME_ROUTE
import com.maruchin.domaindrivenandroid.ui.home.homeScreen

const val HOME_GRAPH_ROUTE = "home-graph"

fun NavGraphBuilder.homeGraph(navController: NavController, onLoggedOut: () -> Unit) {
    navigation(startDestination = HOME_ROUTE, route = HOME_GRAPH_ROUTE) {
        homeScreen(
            onOpenCoupon = { navController.navigateToCouponPreview(it) },
            onLoggedOut = onLoggedOut,
        )
        couponPreviewScreen(
            onBack = { navController.navigateUp() },
        )
    }
}

fun NavController.navigateToHomeGraph() {
    navigate(HOME_GRAPH_ROUTE) {
        popUpTo(graph.findStartDestination().id) {
            inclusive = true
        }
    }
}
