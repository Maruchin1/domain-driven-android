package com.maruchin.domaindrivenandroid.ui.couponPreview

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.maruchin.domaindrivenandroid.ui.DomainDrivenAndroidTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun CouponPreviewScreen(state: CouponPreviewUiState, onBack: () -> Unit, onCollect: () -> Unit) {
    Scaffold(
        topBar = { TopBar(onBack) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
        ) {
            CouponImage(imageUrl = state.imageUrl, isLoading = state.isLoading)
            CouponNameView(couponName = state.couponName, isLoading = state.isLoading)
            PriceView(price = state.price, isLoading = state.isLoading)
            Spacer(modifier = Modifier.weight(1f))
            AnimatedContent(
                targetState = state.activation,
                modifier = Modifier.fillMaxWidth()
            ) { activationCode ->
                when (activationCode) {
                    ActivationUiState.CantCollect -> CantCollectButton(
                        isLoading = state.isLoading,
                    )

                    ActivationUiState.Collect -> CollectButton(
                        isLoading = state.isLoading,
                        onCollect = onCollect,
                    )

                    is ActivationUiState.Active -> ActivationCodeView(activationCode)
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar(onBack: () -> Unit) {
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

@Composable
private fun CouponImage(imageUrl: String, isLoading: Boolean) {
    OutlinedCard(modifier = Modifier.padding(12.dp)) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f / 1f)
                .placeholder(isLoading),
        )
    }
}

@Composable
private fun CouponNameView(couponName: String, isLoading: Boolean) {
    Text(
        text = couponName,
        style = MaterialTheme.typography.displaySmall,
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 20.dp)
            .placeholder(isLoading)
    )
}

@Composable
private fun PriceView(price: String, isLoading: Boolean) {
    Text(
        text = price,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 20.dp)
            .placeholder(isLoading),
    )
}

@Composable
private fun CantCollectButton(isLoading: Boolean) {
    Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp)) {
        Text(
            text = "Not enough points",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = {},
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .placeholder(isLoading),
        ) {
            Text(text = "Offer locked".uppercase())
        }
    }
}

@Composable
private fun CollectButton(isLoading: Boolean, onCollect: () -> Unit) {
    Button(
        onClick = onCollect,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 12.dp)
            .placeholder(isLoading),
    ) {
        Text(text = "Collect".uppercase())
    }
}

@Composable
private fun ActivationCodeView(activationCode: ActivationUiState.Active) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .placeholder(activationCode.isProcessing, highlight = PlaceholderHighlight.shimmer()),
    ) {
        Text(
            text = activationCode.code,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        )
    }
}

@Preview
@Composable
private fun DefaultPreview(@PreviewParameter(UiStateProvider::class) state: CouponPreviewUiState) {
    DomainDrivenAndroidTheme {
        CouponPreviewScreen(state = state, onBack = { }, onCollect = { })
    }
}

private class UiStateProvider : PreviewParameterProvider<CouponPreviewUiState> {
    override val values = sequenceOf(
        CouponPreviewUiState(),
    )
}
