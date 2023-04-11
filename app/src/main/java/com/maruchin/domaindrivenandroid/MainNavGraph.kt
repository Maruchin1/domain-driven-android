package com.maruchin.domaindrivenandroid

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.maruchin.domaindrivenandroid.ui.couponPreview.couponPreviewScreen
import com.maruchin.domaindrivenandroid.ui.couponPreview.navigateToCouponPreview
import com.maruchin.domaindrivenandroid.ui.home.HOME_ROUTE
import com.maruchin.domaindrivenandroid.ui.home.homeScreen

@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HOME_ROUTE) {
        homeScreen(
            onOpenCoupon = { navController.navigateToCouponPreview(it) }
        )
        couponPreviewScreen(
            onBack = { navController.navigateUp() }
        )
    }
}
