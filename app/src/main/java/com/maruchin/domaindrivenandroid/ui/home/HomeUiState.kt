package com.maruchin.domaindrivenandroid.ui.home

import androidx.compose.runtime.Immutable
import com.maruchin.domaindrivenandroid.data.ID
import com.maruchin.domaindrivenandroid.data.coupon.Coupon

@Immutable
data class HomeUiState(
    val coupons: List<CouponUiState> = listOf(
        CouponUiState(),
        CouponUiState(),
        CouponUiState(),
        CouponUiState(),
        CouponUiState(),
    ),
)

@Immutable
data class CouponUiState(
    val id: ID = ID(""),
    val imageUrl: String = "",
    val couponName: String = "00000 00000 00000",
    val price: String = "00000",
    val isLoading: Boolean = true,
) {

    constructor(coupon: Coupon) : this(
        id = coupon.id,
        imageUrl = coupon.image.toString(),
        couponName = coupon.name,
        price = coupon.price.toString(),
        isLoading = false,
    )
}
