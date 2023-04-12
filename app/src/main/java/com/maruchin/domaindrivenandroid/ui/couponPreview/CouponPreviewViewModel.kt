package com.maruchin.domaindrivenandroid.ui.couponPreview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.domaindrivenandroid.data.ID
import com.maruchin.domaindrivenandroid.data.activationCode.ActivationCodesRepository
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
    private val activationCodesRepository: ActivationCodesRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CouponPreviewUiState())
    val uiState = _uiState.asStateFlow()

    fun selectCoupon(couponId: ID) = viewModelScope.launch {
        couponsRepository.getCoupon(couponId)?.let { coupon ->
            _uiState.update {
                CouponPreviewUiState(
                    isLoading = false,
                    couponId = coupon.id,
                    imageUrl = coupon.image.toString(),
                    couponName = coupon.name,
                    price = coupon.price.toString(),
                )
            }
        }
    }

    fun collectCoupon() = viewModelScope.launch {
        val couponId = _uiState.value.couponId
        _uiState.update {
            it.copy(activationCode = ActivationCodeUiState.Active())
        }
        val activationCode = activationCodesRepository.getActivationCodeForCoupon(couponId)
        _uiState.update {
            it.copy(activationCode = ActivationCodeUiState.Active(activationCode))
        }
    }
}
