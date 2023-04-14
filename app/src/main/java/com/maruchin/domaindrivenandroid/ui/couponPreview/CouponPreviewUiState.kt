package com.maruchin.domaindrivenandroid.ui.couponPreview

import com.maruchin.domaindrivenandroid.data.activationCode.ActivationCode
import com.maruchin.domaindrivenandroid.domain.coupon.CollectableCoupon
import com.maruchin.domaindrivenandroid.ui.placeholderCollectableCoupon

data class CouponPreviewUiState(
    val coupon: CollectableCoupon = placeholderCollectableCoupon(),
    val isLoading: Boolean = true,
    val couponStatus: CouponStatus = CouponStatus.NotCollected,
    val failedToLoadCoupon: Boolean = false,
)

sealed class CouponStatus {

    object NotCollected : CouponStatus()

    object CollectingInProgress : CouponStatus()

    object FailedToCollect : CouponStatus()

    data class Collected(val activationCode: ActivationCode) : CouponStatus()
}
