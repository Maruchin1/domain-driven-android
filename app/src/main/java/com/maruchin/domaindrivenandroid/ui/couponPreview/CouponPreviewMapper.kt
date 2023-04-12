package com.maruchin.domaindrivenandroid.ui.couponPreview

import com.maruchin.domaindrivenandroid.data.activationCode.ActivationCode
import com.maruchin.domaindrivenandroid.domain.coupon.CollectableCoupon
import com.maruchin.domaindrivenandroid.ui.format

fun mapCouponSelected(
    state: CouponPreviewUiState,
    collectableCoupon: CollectableCoupon
): CouponPreviewUiState {
    return state.copy(
        isLoading = false,
        couponId = collectableCoupon.coupon.id,
        imageUrl = collectableCoupon.coupon.image.toString(),
        couponName = collectableCoupon.coupon.name,
        price = collectableCoupon.coupon.price.format(),
        activation = if (collectableCoupon.canCollect) {
            ActivationUiState.Collect
        } else {
            ActivationUiState.CantCollect
        }
    )
}

fun mapActivationStarted(state: CouponPreviewUiState): CouponPreviewUiState {
    return state.copy(activation = ActivationUiState.Active(isProcessing = true))
}

fun mapActivationCompleted(
    state: CouponPreviewUiState,
    activationCode: ActivationCode
): CouponPreviewUiState {
    return state.copy(
        activation = ActivationUiState.Active(
            code = activationCode.value,
            isProcessing = false,
        )
    )
}
