package com.maruchin.domaindrivenandroid.ui.home

import androidx.compose.runtime.Immutable
import com.maruchin.domaindrivenandroid.data.units.ID

@Immutable
data class HomeUiState(
    val coupons: List<CouponItemUiState> = listOf(
        CouponItemUiState(),
        CouponItemUiState(),
        CouponItemUiState(),
        CouponItemUiState(),
        CouponItemUiState(),
    ),
    val showError: Boolean = false,
)

@Immutable
data class CouponItemUiState(
    val id: ID = ID(""),
    val imageUrl: String = "",
    val couponName: String = "00000 00000 00000",
    val price: String = "00000",
    val isLoading: Boolean = true,
    val canCollect: Boolean = false,
)
