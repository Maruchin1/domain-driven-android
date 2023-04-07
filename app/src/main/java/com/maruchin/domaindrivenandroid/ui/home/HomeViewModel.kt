package com.maruchin.domaindrivenandroid.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.domaindrivenandroid.data.category.CategoriesRepository
import com.maruchin.domaindrivenandroid.data.category.Category
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val couponsRepository: CouponsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    fun selectCategory(category: Category) {
        _uiState.update { it.copy(selectedCategory = category) }
        loadCoupons()
    }

    private fun loadCategories() = viewModelScope.launch {
        _uiState.update {
            it.copy(loadingCategories = true)
        }
        val categories = categoriesRepository.getAllCategories()
        _uiState.update {
            it.copy(
                categories = categories,
                loadingCategories = false,
                selectedCategory = categories.first()
            )
        }
        loadCoupons()
    }

    private fun loadCoupons() = viewModelScope.launch {
        _uiState.update {
            it.copy(loadingCoupons = true)
        }
        _uiState.value.selectedCategory?.let { category ->
            val coupons = couponsRepository.getCouponsFromCategory(category)
            _uiState.update { it.copy(couponsFromCategory = coupons, loadingCoupons = false) }
        }
    }
}
