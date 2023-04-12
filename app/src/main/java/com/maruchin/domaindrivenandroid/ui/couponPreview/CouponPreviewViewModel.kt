package com.maruchin.domaindrivenandroid.ui.couponPreview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.domaindrivenandroid.data.units.ID
import com.maruchin.domaindrivenandroid.data.activationCode.ActivationCodesRepository
import com.maruchin.domaindrivenandroid.domain.coupon.GetCouponToUnlockUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CouponPreviewViewModel @Inject constructor(
    private val getCouponToUnlockUseCase: GetCouponToUnlockUseCase,
    private val activationCodesRepository: ActivationCodesRepository,
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> handleError() }
    private val _uiState = MutableStateFlow(CouponPreviewUiState())
    val uiState = _uiState.asStateFlow()

    fun selectCoupon(couponId: ID) = viewModelScope.launch(exceptionHandler) {
        val couponToUnlock = getCouponToUnlockUseCase(couponId).let(::checkNotNull)
        _uiState.update { mapCouponSelected(it, couponToUnlock) }
    }

    fun collectCoupon() = viewModelScope.launch(exceptionHandler) {
        val couponId = _uiState.value.couponId
        _uiState.update { mapActivationStarted(it) }
        val activationCode = activationCodesRepository.getActivationCodeForCoupon(couponId)
        _uiState.update { mapActivationCompleted(it, activationCode) }
    }

    fun consumeError() {
        _uiState.update { it.copy(showError = false) }
    }

    private fun handleError() {
        _uiState.update { it.copy(showError = true) }
    }
}
