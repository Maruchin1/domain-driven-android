package com.maruchin.domaindrivenandroid.ui.home

import androidx.compose.runtime.Immutable
import com.maruchin.domaindrivenandroid.data.coupon.Coupon

@Immutable
data class HomeUiState(
    val coupons: List<Coupon> = emptyList(),
    val loading: Boolean = true,
)
