package com.maruchin.domaindrivenandroid.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.domain.coupon.GetAllCollectableCouponsUseCase
import com.maruchin.domaindrivenandroid.ui.logError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCollectableCouponsUseCase: GetAllCollectableCouponsUseCase,
    private val accountRepository: AccountRepository,
) : ViewModel() {

    private val allCoupons = getAllCollectableCouponsUseCase()
    private val account = accountRepository.getLoggedInAccount().filterNotNull()

    val uiState: StateFlow<HomeUiState> = combine(
        allCoupons,
        account
    ) { allCoupons, account ->
        HomeUiState(coupons = allCoupons, isLoading = false, myPoints = account.collectedPoints)
    }.catch { error ->
        logError(error)
        emit(HomeUiState(coupons = emptyList(), isLoading = false, failedToLoadCoupons = true))
    }.stateIn(viewModelScope, SharingStarted.Lazily, HomeUiState())
}
