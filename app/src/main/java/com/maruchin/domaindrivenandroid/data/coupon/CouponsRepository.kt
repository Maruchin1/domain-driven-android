package com.maruchin.domaindrivenandroid.data.coupon

import com.maruchin.domaindrivenandroid.data.category.Category
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CouponsRepository @Inject constructor() {

    suspend fun getCouponsFromCategory(category: Category): List<Coupon> {
        return when (category.id) {
            "for_home" -> sampleForHomeCoupons
            else -> emptyList()
        }
    }
}
