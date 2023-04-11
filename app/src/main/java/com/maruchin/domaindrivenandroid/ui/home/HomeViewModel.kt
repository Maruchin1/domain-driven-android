package com.maruchin.domaindrivenandroid.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val couponsRepository: CouponsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadCoupons()
    }

    private fun loadCoupons() = viewModelScope.launch {
        _uiState.update {
            it.copy(loading = true)
        }
        val coupons = couponsRepository.getAllCoupons()
        _uiState.update {
            it.copy(loading = false, coupons = coupons)
        }
    }
}
