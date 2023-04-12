package com.maruchin.domaindrivenandroid.ui.home

import com.maruchin.domaindrivenandroid.domain.coupon.CouponToUnlock
import com.maruchin.domaindrivenandroid.ui.format

fun mapCouponsLoaded(state: HomeUiState, couponsToUnlock: List<CouponToUnlock>): HomeUiState {
    return state.copy(
        coupons = couponsToUnlock.map { couponToUnlock ->
            CouponItemUiState(
                id = couponToUnlock.coupon.id,
                imageUrl = couponToUnlock.coupon.image.toString(),
                couponName = couponToUnlock.coupon.name,
                price = couponToUnlock.coupon.price.format(),
                isLoading = false,
                isUnlocked = couponToUnlock.coupon.unlocked,
                canUnlock = couponToUnlock.canUnlock,
            )
        }
    )
}
