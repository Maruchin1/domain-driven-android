package com.maruchin.domaindrivenandroid.ui

import android.util.Log
import androidx.lifecycle.ViewModel

fun ViewModel.logError(t: Throwable) {
    Log.e(this::class.simpleName, t.stackTraceToString())
}
