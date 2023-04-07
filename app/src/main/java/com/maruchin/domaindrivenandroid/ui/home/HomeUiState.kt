package com.maruchin.domaindrivenandroid.ui.home

import androidx.compose.runtime.Immutable
import com.maruchin.domaindrivenandroid.data.category.Category
import com.maruchin.domaindrivenandroid.data.coupon.Coupon

@Immutable
data class HomeUiState(
    val categories: List<Category> = emptyList(),
    val loadingCategories: Boolean = true,
    val selectedCategory: Category? = null,
    val couponsFromCategory: List<Coupon> = emptyList(),
    val loadingCoupons: Boolean = true,
)
