package com.maruchin.domaindrivenandroid.ui.couponPreview

import com.maruchin.domaindrivenandroid.domain.coupon.CollectableCoupon
import com.maruchin.domaindrivenandroid.ui.placeholderCollectableCoupon

data class CouponPreviewUiState(
    val coupon: CollectableCoupon = placeholderCollectableCoupon(),
    val isLoading: Boolean = true,
    val isCollecting: Boolean = false,
    val failedToLoadCoupon: Boolean = false,
)

fun CouponPreviewUiState.getCouponStatus(): CouponStatus {
    return when {
        isCollecting -> CouponStatus.COLLECTING
        coupon.coupon.activationCode != null -> CouponStatus.COLLECTED
        else -> CouponStatus.NOT_COLLECTED
    }
}

enum class CouponStatus {
    NOT_COLLECTED, COLLECTING, COLLECTED
}
