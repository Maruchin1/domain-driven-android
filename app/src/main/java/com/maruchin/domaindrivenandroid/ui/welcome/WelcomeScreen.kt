package com.maruchin.domaindrivenandroid.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun WelcomeScreen(onSignUp: () -> Unit, onSignIn: () -> Unit) {
    Scaffold { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                text = "Welcome",
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 48.dp),
            )
            Image(
                painter = painterResource(R.drawable.welcome),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onSignUp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 16.dp, bottom = 6.dp)
                    .fillMaxWidth(),
            ) {
                Text(text = "Sign Up")
            }
            FilledTonalButton(
                onClick = onSignIn,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 16.dp, top = 6.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Sign In")
            }
        }
    }
}

@Preview
@Composable
private fun WelcomePreview() {
    DomainDrivenAndroidTheme {
        WelcomeScreen(onSignUp = {}, onSignIn = {})
    }
}
