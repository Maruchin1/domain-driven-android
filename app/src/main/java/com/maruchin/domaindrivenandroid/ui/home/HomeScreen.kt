package com.maruchin.domaindrivenandroid.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.material.placeholder
import com.maruchin.domaindrivenandroid.data.account.sampleAccount
import com.maruchin.domaindrivenandroid.data.units.ID
import com.maruchin.domaindrivenandroid.data.units.Points
import com.maruchin.domaindrivenandroid.domain.coupon.CollectableCoupon
import com.maruchin.domaindrivenandroid.domain.coupon.sampleCollectableCoupons
import com.maruchin.domaindrivenandroid.ui.DomainDrivenAndroidTheme
import com.maruchin.domaindrivenandroid.ui.format

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeUiState,
    onOpenCoupon: (ID) -> Unit,
    onLogout: () -> Unit,
    onLoggedOut: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    if (state.loggedOut) {
        LaunchedEffect(Unit) { onLoggedOut() }
    }
    Scaffold(
        topBar = { TopBar(scrollBehavior, state.myPoints, onLogout) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            CouponsListView(
                scrollBehavior = scrollBehavior,
                coupons = state.coupons,
                isLoading = state.isLoading,
                onOpenCoupon = onOpenCoupon,
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar(scrollBehavior: TopAppBarScrollBehavior, points: Points?, onLogout: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = buildAnnotatedString {
                    append("My points: ")
                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append(points?.format() ?: "")
                    }
                },
            )
        },
        scrollBehavior = scrollBehavior,
        actions = {
            IconButton(onClick = onLogout) {
                Icon(imageVector = Icons.Outlined.ExitToApp, contentDescription = "Logout")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CouponsListView(
    scrollBehavior: TopAppBarScrollBehavior,
    coupons: List<CollectableCoupon>,
    isLoading: Boolean,
    onOpenCoupon: (ID) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(6.dp),
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) {
        items(coupons) { coupon ->
            CouponView(
                coupon = coupon,
                isLoading = isLoading,
                onClick = { onOpenCoupon(coupon.coupon.id) })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CouponView(coupon: CollectableCoupon, isLoading: Boolean, onClick: () -> Unit) {
    val density = LocalDensity.current.density
    var couponNamePadding by remember { mutableStateOf(0.dp) }
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier
            .padding(6.dp)
            .alpha(if (coupon.canCollect) 1f else 0.6f),
    ) {
        AsyncImage(
            model = coupon.coupon.image.toString().takeIf { it.isNotBlank() },
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f / 1f)
                .placeholder(isLoading),
        )
        Text(
            text = coupon.coupon.name,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            onTextLayout = {
                val lineCount = it.lineCount
                val height = (it.size.height / density).dp
                couponNamePadding = if (lineCount > 1) 0.dp else height
            },
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(top = 12.dp, bottom = couponNamePadding)
                .placeholder(isLoading)
        )
        Text(
            text = coupon.coupon.points.format(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(12.dp)
                .placeholder(isLoading),
        )
    }
}

@Preview
@Composable
private fun DefaultPreview(@PreviewParameter(UiStateProvider::class) state: HomeUiState) {
    DomainDrivenAndroidTheme {
        HomeScreen(state = state, onOpenCoupon = {}, onLogout = {}, onLoggedOut = {})
    }
}

private class UiStateProvider : PreviewParameterProvider<HomeUiState> {
    override val values = sequenceOf(
        HomeUiState(),
        HomeUiState(
            myPoints = sampleAccount.collectedPoints,
            coupons = sampleCollectableCoupons,
            isLoading = false,
        )
    )
}
