package com.maruchin.domaindrivenandroid.ui.home

import com.maruchin.domaindrivenandroid.domain.coupon.CollectableCoupon
import com.maruchin.domaindrivenandroid.ui.format

fun mapCouponsLoaded(state: HomeUiState, collectableCoupons: List<CollectableCoupon>): HomeUiState {
    return state.copy(
        coupons = collectableCoupons.map { couponToUnlock ->
            CouponItemUiState(
                id = couponToUnlock.coupon.id,
                imageUrl = couponToUnlock.coupon.image.toString(),
                couponName = couponToUnlock.coupon.name,
                price = couponToUnlock.coupon.price.format(),
                isLoading = false,
                canCollect = couponToUnlock.canCollect,
            )
        }
    )
}
