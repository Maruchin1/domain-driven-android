package com.maruchin.domaindrivenandroid.ui.personaldataform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maruchin.domaindrivenandroid.data.values.Email
import com.maruchin.domaindrivenandroid.ui.DomainDrivenAndroidTheme
import com.maruchin.domaindrivenandroid.ui.FieldErrorView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDataFormScreen(
    state: PersonalDataFormUiState,
    onBack: () -> Unit,
    onProceed: (Email) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val emailFieldState = rememberEmailFieldState(state.registrationRequest?.email)

    fun proceed() {
        emailFieldState.completeEmail?.let(onProceed)
    }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "Enter your personal data") },
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Navigate up"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            TextField(
                value = emailFieldState.value ?: "",
                onValueChange = { emailFieldState.value = it },
                singleLine = true,
                label = {
                    Text(text = "Email")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Email, contentDescription = null)
                },
                isError = emailFieldState.error != null,
                supportingText = {
                    FieldErrorView(error = emailFieldState.error)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 24.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { proceed() },
                enabled = emailFieldState.isValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 16.dp)
            ) {
                Text(text = "Proceed")
            }
        }
    }
}

@Preview
@Composable
private fun PersonalDataFormPreview() {
    DomainDrivenAndroidTheme {
        PersonalDataFormScreen(state = PersonalDataFormUiState(), onBack = {}, onProceed = {})
    }
}

