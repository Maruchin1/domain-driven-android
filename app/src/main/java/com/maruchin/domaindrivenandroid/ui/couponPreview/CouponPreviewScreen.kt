package com.maruchin.domaindrivenandroid.ui.couponPreview

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.maruchin.domaindrivenandroid.data.coupon.ActivationCode
import com.maruchin.domaindrivenandroid.data.values.Name
import com.maruchin.domaindrivenandroid.data.coupon.sampleActivationCode
import com.maruchin.domaindrivenandroid.domain.coupon.sampleCollectableCoupons
import com.maruchin.domaindrivenandroid.ui.DomainDrivenAndroidTheme
import com.maruchin.domaindrivenandroid.ui.format
import com.maruchin.domaindrivenandroid.ui.formatSeconds

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
            CouponImage(
                imageUrl = state.coupon.coupon.image.toString(),
                isLoading = state.isLoading
            )
            CouponNameView(couponName = state.coupon.coupon.name, isLoading = state.isLoading)
            PointsView(price = state.coupon.coupon.points.format(), isLoading = state.isLoading)
            Spacer(modifier = Modifier.weight(1f))
            AnimatedContent(targetState = state.getCouponStatus()) { status ->
                when (status) {
                    CouponStatus.NOT_COLLECTED -> CollectButton(
                        isLoading = state.isLoading,
                        canCollect = state.coupon.canCollect,
                        onCollect = onCollect,
                    )

                    CouponStatus.COLLECTING -> ActivationCodeView(code = null)
                    CouponStatus.COLLECTED -> ActivationCodeView(code = state.coupon.coupon.activationCode)
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
private fun CouponNameView(couponName: Name, isLoading: Boolean) {
    Text(
        text = couponName.value,
        style = MaterialTheme.typography.displaySmall,
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 20.dp)
            .placeholder(isLoading)
    )
}

@Composable
private fun PointsView(price: String, isLoading: Boolean) {
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
private fun CollectButton(isLoading: Boolean, canCollect: Boolean, onCollect: () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp)) {
        if (!canCollect) {
            Text(
                text = "Not enough points",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            )
        }
        Button(
            onClick = onCollect,
            enabled = !isLoading && canCollect,
            modifier = Modifier
                .fillMaxWidth()
                .placeholder(isLoading),
        ) {
            Text(text = "Collect".uppercase())
        }
    }
}

@Composable
private fun ActivationCodeView(code: ActivationCode?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .placeholder(code == null, highlight = PlaceholderHighlight.shimmer()),
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = code?.value ?: "",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = code?.remainingTime?.formatSeconds() ?: "",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
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
        CouponPreviewUiState(
            coupon = sampleCollectableCoupons[0],
            isLoading = false,
        ),
        CouponPreviewUiState(
            coupon = sampleCollectableCoupons[1],
            isLoading = false,
        ),
        CouponPreviewUiState(
            coupon = sampleCollectableCoupons[1],
            isLoading = false,
            isCollecting = true,
        ),
        CouponPreviewUiState(
            coupon = sampleCollectableCoupons[1].copy(
                coupon = sampleCollectableCoupons[1].coupon.collect(sampleActivationCode)
            ),
            isLoading = false,
        )
    )
}
