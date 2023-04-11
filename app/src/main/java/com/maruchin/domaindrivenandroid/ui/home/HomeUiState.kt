package com.maruchin.domaindrivenandroid.ui.home

import androidx.compose.runtime.Immutable
import com.maruchin.domaindrivenandroid.data.ID
import com.maruchin.domaindrivenandroid.data.coupon.Coupon

@Immutable
data class HomeUiState(
    val coupons: List<CouponUiState> = emptyList(),
    val loading: Boolean = true,
)

@Immutable
data class CouponUiState(
    val id: ID,
    val imageUrl: String,
    val couponName: String,
    val price: String,
) {

    constructor(coupon: Coupon) : this(
        id = coupon.id,
        imageUrl = coupon.image.toString(),
        couponName = coupon.name,
        price = coupon.price.toString(),
    )
}
