package com.maruchin.domaindrivenandroid.ui.couponPreview

import androidx.compose.runtime.Immutable
import com.maruchin.domaindrivenandroid.data.coupon.Coupon

@Immutable
sealed class CouponPreviewUiState {

    object Loading : CouponPreviewUiState()

    class Ready(
        val imageUrl: String,
        val couponName: String,
        val price: String,
    ) : CouponPreviewUiState() {

        constructor(coupon: Coupon) : this(
            imageUrl = coupon.image.toString(),
            couponName = coupon.name,
            price = coupon.price.toString(),
        )
    }
}
