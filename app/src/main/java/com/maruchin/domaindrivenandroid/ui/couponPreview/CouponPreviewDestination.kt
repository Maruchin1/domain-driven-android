package com.maruchin.domaindrivenandroid.ui.couponPreview

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maruchin.domaindrivenandroid.data.ID

const val COUPON_PREVIEW_ROUTE = "coupon-preview"
const val COUPON_IN = "couponId"

fun NavGraphBuilder.couponPreviewScreen(onBack: () -> Unit) {
    composable("$COUPON_PREVIEW_ROUTE/{$COUPON_IN}") {
        val couponId = ID(it.arguments?.getString(COUPON_IN) ?: "")
        val viewModel = hiltViewModel<CouponPreviewViewModel>()
        val state by viewModel.uiState.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.selectCoupon(couponId)
        }
        CouponPreviewScreen(state = state, onBack = onBack, onCollect = viewModel::collectCoupon)
    }
}

fun NavController.navigateToCouponPreview(couponId: ID) {
    navigate("$COUPON_PREVIEW_ROUTE/${couponId.value}")
}
