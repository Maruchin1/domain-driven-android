package com.maruchin.domaindrivenandroid.ui.couponPreview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.domaindrivenandroid.data.units.ID
import com.maruchin.domaindrivenandroid.domain.coupon.CollectCouponUseCase
import com.maruchin.domaindrivenandroid.domain.coupon.GetCollectableCouponUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CouponPreviewViewModel @Inject constructor(
    private val getCollectableCouponUseCase: GetCollectableCouponUseCase,
    private val collectCouponUseCase: CollectCouponUseCase,
) : ViewModel() {

    private val couponId = MutableStateFlow<ID?>(null)
    private val couponStatus = MutableStateFlow<CouponStatus>(CouponStatus.NotCollected)

    private val coupon = couponId.filterNotNull().flatMapLatest { couponId ->
        getCollectableCouponUseCase(couponId)
    }.filterNotNull()

    val uiState: StateFlow<CouponPreviewUiState> = combine(
        coupon,
        couponStatus,
    ) { coupon, couponStatus ->
        CouponPreviewUiState(coupon = coupon, isLoading = false, couponStatus = couponStatus)
    }.catch {
        emit(CouponPreviewUiState(failedToLoadCoupon = true))
    }.stateIn(viewModelScope, SharingStarted.Lazily, CouponPreviewUiState())

    fun selectCoupon(couponId: ID) {
        this.couponId.value = couponId
    }

    fun collectCoupon() = viewModelScope.launch {
        try {
            couponStatus.value = CouponStatus.CollectingInProgress
            val couponId = checkNotNull(couponId.value)
            val activationCode = collectCouponUseCase(couponId)
            couponStatus.value = CouponStatus.Collected(activationCode)
        } catch (_: Throwable) {
            couponStatus.value = CouponStatus.FailedToCollect
        }
    }
}
