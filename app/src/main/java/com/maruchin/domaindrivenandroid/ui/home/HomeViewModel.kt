package com.maruchin.domaindrivenandroid.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.domaindrivenandroid.domain.coupon.GetAllCouponsToUnlockUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCouponsToUnlockUseCase: GetAllCouponsToUnlockUseCase,
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> handleError() }
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadCoupons()
    }

    fun consumeError() {
        _uiState.update { it.copy(showError = false) }
    }

    private fun loadCoupons() = viewModelScope.launch(exceptionHandler) {
        val couponsToUnlock = getAllCouponsToUnlockUseCase()
        _uiState.update { mapCouponsLoaded(it, couponsToUnlock) }
    }

    private fun handleError() {
        _uiState.update { it.copy(showError = true) }
    }
}
