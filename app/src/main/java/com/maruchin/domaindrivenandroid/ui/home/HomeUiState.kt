package com.maruchin.domaindrivenandroid.ui.home

import androidx.compose.runtime.Immutable
import com.maruchin.domaindrivenandroid.data.units.Points
import com.maruchin.domaindrivenandroid.domain.coupon.CollectableCoupon
import com.maruchin.domaindrivenandroid.ui.placeholderCollectableCoupon

@Immutable
data class HomeUiState(
    val myPoints: Points? = null,
    val coupons: List<CollectableCoupon> = getPlaceholderCoupons(),
    val isLoading: Boolean = true,
    val failedToLoadCoupons: Boolean = false
)

private fun getPlaceholderCoupons(): List<CollectableCoupon> {
    return (1..5).map(::placeholderCollectableCoupon)
}
