package com.maruchin.domaindrivenandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.maruchin.domaindrivenandroid.ui.DomainDrivenAndroidTheme
import com.maruchin.domaindrivenandroid.ui.MainNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DomainDrivenAndroidTheme {
                MainNavGraph()
            }
        }
    }
}
