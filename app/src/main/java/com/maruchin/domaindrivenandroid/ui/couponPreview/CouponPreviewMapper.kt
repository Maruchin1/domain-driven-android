package com.maruchin.domaindrivenandroid.ui.couponPreview

import com.maruchin.domaindrivenandroid.data.activationCode.ActivationCode
import com.maruchin.domaindrivenandroid.domain.coupon.CouponToUnlock
import com.maruchin.domaindrivenandroid.ui.format

fun mapCouponSelected(
    state: CouponPreviewUiState,
    couponToUnlock: CouponToUnlock
): CouponPreviewUiState {
    return state.copy(
        isLoading = false,
        couponId = couponToUnlock.coupon.id,
        imageUrl = couponToUnlock.coupon.image.toString(),
        couponName = couponToUnlock.coupon.name,
        price = couponToUnlock.coupon.price.format(),
    )
}

fun mapActivationStarted(state: CouponPreviewUiState): CouponPreviewUiState {
    return state.copy(activationCode = ActivationCodeUiState.Active(isProcessing = true))
}

fun mapActivationCompleted(
    state: CouponPreviewUiState,
    activationCode: ActivationCode
): CouponPreviewUiState {
    return state.copy(
        activationCode = ActivationCodeUiState.Active(
            code = activationCode.value,
            isProcessing = false,
        )
    )
}
