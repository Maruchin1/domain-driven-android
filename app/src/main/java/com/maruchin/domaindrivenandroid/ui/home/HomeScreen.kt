package com.maruchin.domaindrivenandroid.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.material.placeholder
import com.maruchin.domaindrivenandroid.data.units.ID
import com.maruchin.domaindrivenandroid.ui.DomainDrivenAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(state: HomeUiState, onOpenCoupon: (ID) -> Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = { TopBar(scrollBehavior) }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(6.dp),
            modifier = Modifier
                .padding(paddingValues)
                .nestedScroll(scrollBehavior.nestedScrollConnection),
        ) {
            items(state.coupons) { coupon ->
                CouponView(state = coupon, onClick = { onOpenCoupon(coupon.id) })
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar(scrollBehavior: TopAppBarScrollBehavior) {
    CenterAlignedTopAppBar(
        title = { Text(text = "My Coupons") },
        scrollBehavior = scrollBehavior,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CouponView(state: CouponItemUiState, onClick: () -> Unit) {
    val density = LocalDensity.current.density
    var couponNamePadding by remember { mutableStateOf(0.dp) }
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier
            .padding(6.dp)
            .alpha(if (state.canCollect) 1f else 0.6f),
    ) {
        AsyncImage(
            model = state.imageUrl.takeIf { it.isNotBlank() },
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f / 1f)
                .placeholder(state.isLoading),
        )
        Text(
            text = state.couponName,
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
                .placeholder(state.isLoading)
        )
        Text(
            text = state.price,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(12.dp)
                .placeholder(state.isLoading),
        )
    }
}

@Preview
@Composable
private fun DefaultPreview(@PreviewParameter(UiStateProvider::class) state: HomeUiState) {
    DomainDrivenAndroidTheme {
        HomeScreen(state = state, onOpenCoupon = {})
    }
}

private class UiStateProvider : PreviewParameterProvider<HomeUiState> {
    override val values = sequenceOf(
        HomeUiState(),
    )
}
