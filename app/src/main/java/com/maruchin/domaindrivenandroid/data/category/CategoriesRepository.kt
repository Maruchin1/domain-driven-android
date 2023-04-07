package com.maruchin.domaindrivenandroid.data.category

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepository @Inject constructor() {

    suspend fun getAllCategories(): List<Category> {
        return sampleCategories
    }
}
