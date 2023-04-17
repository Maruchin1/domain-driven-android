package com.maruchin.domaindrivenandroid.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.maruchin.domaindrivenandroid.ui.DomainDrivenAndroidTheme
import com.maruchin.domaindrivenandroid.ui.navigation.MainNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DomainDrivenAndroidTheme {
                val viewModel = hiltViewModel<MainViewModel>()
                val isLoggedIn by viewModel.isLoggedIn.collectAsState()
                isLoggedIn?.let {
                    MainNavHost(isLoggedIn = it)
                }
            }
        }
    }
}
