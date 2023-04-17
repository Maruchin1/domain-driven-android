package com.maruchin.domaindrivenandroid.ui.termsandconditions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maruchin.domaindrivenandroid.ui.DomainDrivenAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndConditionsScreen(
    state: TermsAndConditionsUiState,
    onBack: () -> Unit,
    onContinue: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    var accepted by remember(state.registrationRequest?.termsAndConditionsAccepted) {
        mutableStateOf(state.registrationRequest?.termsAndConditionsAccepted ?: false)
    }
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = "Terms & Conditions")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Navigate up",
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
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc non efficitur libero. Nam mattis vel justo non feugiat. Aliquam iaculis tempor mauris, nec sodales elit. Etiam vitae quam dignissim, pulvinar ipsum vitae, tempus nulla. Etiam in eros ac mauris tempus mollis vitae et tortor. Fusce ultricies, sapien at malesuada hendrerit, nulla lorem maximus eros, nec finibus nisi lorem eu erat. Duis posuere velit fermentum mi elementum, a dictum ex consequat. Donec euismod rutrum molestie. Ut mattis lectus et vulputate aliquam. Aliquam accumsan lectus sapien, sit amet sodales enim malesuada eu. Morbi ut vulputate eros, sit amet cursus mauris. Fusce at neque tempor, varius dolor et, imperdiet magna. Curabitur efficitur, orci in auctor venenatis, augue dolor maximus risus, tempus pellentesque massa mi sit amet felis. Pellentesque vulputate viverra fermentum. Suspendisse a ullamcorper purus.\n" +
                        "\n" +
                        "Fusce eget blandit tellus. Ut blandit justo vitae leo ornare, a tincidunt dolor laoreet. Phasellus ornare blandit nisl, sed mattis augue accumsan viverra. Sed aliquam justo ut urna tristique porta. Praesent est augue, ultrices eu velit vitae, euismod interdum sem. Cras finibus eros vel quam mollis, id maximus sem tempus. Quisque vel mi tincidunt ante laoreet mollis. Vivamus dictum mi lacus, at egestas dolor posuere sed. Morbi mattis porttitor est, id egestas urna porttitor at.",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(20.dp),
            )
            Row(
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = accepted, onCheckedChange = { accepted = it })
                Text(
                    text = "I agree with the Terms and Conditions",
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onContinue,
                enabled = accepted,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 16.dp),
            ) {
                Text(text = "Continue")
            }
        }
    }
}

@Preview
@Composable
private fun TermsAndConditionsPreview() {
    DomainDrivenAndroidTheme {
        TermsAndConditionsScreen(state = TermsAndConditionsUiState(), onBack = {}, onContinue = {})
    }
}
