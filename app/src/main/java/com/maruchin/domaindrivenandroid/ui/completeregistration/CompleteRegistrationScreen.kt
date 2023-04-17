package com.maruchin.domaindrivenandroid.ui.completeregistration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maruchin.domaindrivenandroid.R
import com.maruchin.domaindrivenandroid.ui.DomainDrivenAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteRegistrationScreen(
    state: CompleteRegistrationUiState,
    onBack: () -> Unit,
    onComplete: () -> Unit,
    onCompletedSuccessfully: () -> Unit,
) {
    if (state.completed) {
        LaunchedEffect(Unit) { onCompletedSuccessfully() }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Text(
                text = "All good!",
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 48.dp),
            )
            Image(
                painter = painterResource(R.drawable.all_good),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onComplete,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 16.dp)
            ) {
                Text(text = "Complete")
            }
        }
    }
}

@Preview
@Composable
private fun CompleteRegistration() {
    DomainDrivenAndroidTheme {
        CompleteRegistrationScreen(
            state = CompleteRegistrationUiState(),
            onBack = {},
            onComplete = {},
            onCompletedSuccessfully = {},
        )
    }
}
