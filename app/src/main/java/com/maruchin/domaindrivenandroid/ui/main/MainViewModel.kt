package com.maruchin.domaindrivenandroid.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.domaindrivenandroid.data.account.AccountRepository
import com.maruchin.domaindrivenandroid.ui.logError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
) : ViewModel() {

    val isLoggedIn: StateFlow<Boolean?> = accountRepository
        .getLoggedInAccount()
        .map { it != null }
        .catch { logError(it) }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
}
