package com.maruchin.domaindrivenandroid.ui.couponPreview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CouponPreviewScreen(state: CouponPreviewUiState, onBack: () -> Unit, onCollect: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My Coupons") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Navigate up",
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        when (state) {
            CouponPreviewUiState.Loading -> {}
            is CouponPreviewUiState.Ready -> {
                Column(modifier = Modifier.padding(paddingValues)) {
                    OutlinedCard(modifier = Modifier.padding(12.dp)) {
                        AsyncImage(
                            model = state.imageUrl,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.FillWidth,
                        )
                    }
                    Text(
                        text = state.couponName,
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 20.dp)
                    )
                    Text(
                        text = state.price,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 20.dp),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = onCollect,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 12.dp),
                    ) {
                        Text(text = "Collect".uppercase())
                    }
                }
            }
        }
    }
}
