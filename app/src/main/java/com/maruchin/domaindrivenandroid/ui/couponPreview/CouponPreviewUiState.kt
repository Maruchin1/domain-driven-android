package com.maruchin.domaindrivenandroid.ui.couponPreview

import androidx.compose.runtime.Immutable
import com.maruchin.domaindrivenandroid.data.units.ID

@Immutable
data class CouponPreviewUiState(
    val isLoading: Boolean = true,
    val couponId: ID = ID(""),
    val imageUrl: String = "",
    val couponName: String = "00000 00000 00000",
    val price: String = "00000",
    val activation: ActivationUiState = ActivationUiState.Collect,
    val showError: Boolean = false,
)

sealed class ActivationUiState {

    object CantCollect : ActivationUiState()

    object Collect : ActivationUiState()

    @Immutable
    data class Active(
        val code: String = "00000",
        val isProcessing: Boolean = true,
    ) : ActivationUiState()
}
