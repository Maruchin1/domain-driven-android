package com.maruchin.domaindrivenandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maruchin.domaindrivenandroid.ui.theme.DomainDrivenAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DomainDrivenAndroidTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Domain Driven Android") })
        }
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        ) {
            Greeting()
        }
    }
}

@Composable
fun Greeting() {
    Text(
        text = "Hello Android!",
        modifier = Modifier.padding(top = 32.dp),
        style = MaterialTheme.typography.headlineLarge,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DomainDrivenAndroidTheme {
        MainScreen()
    }
}
