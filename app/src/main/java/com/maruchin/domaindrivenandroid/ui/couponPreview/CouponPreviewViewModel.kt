package com.maruchin.domaindrivenandroid.ui.couponPreview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.domaindrivenandroid.data.ID
import com.maruchin.domaindrivenandroid.data.coupon.CouponsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CouponPreviewViewModel @Inject constructor(
    private val couponsRepository: CouponsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<CouponPreviewUiState>(CouponPreviewUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun selectCoupon(couponId: ID) = viewModelScope.launch {
        couponsRepository.getCoupon(couponId)?.let { coupon ->
            _uiState.update {
                CouponPreviewUiState.Ready(coupon)
            }
        }
    }
}
