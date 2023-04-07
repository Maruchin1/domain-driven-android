package com.maruchin.domaindrivenandroid.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.maruchin.domaindrivenandroid.data.category.Category
import com.maruchin.domaindrivenandroid.data.coupon.Coupon
import com.maruchin.domaindrivenandroid.ui.common.format
import com.maruchin.domaindrivenandroid.ui.common.icon
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(state: HomeUiState, onSelectCategory: (Category) -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    fun openDrawer() {
        scope.launch { drawerState.open() }
    }

    fun closeDrawer() {
        scope.launch { drawerState.close() }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            HomeDrawer(
                categories = state.categories,
                selectedCategory = state.selectedCategory,
                onSelectCategory = { closeDrawer(); onSelectCategory(it) }
            )
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = state.selectedCategory?.name ?: "") },
                    navigationIcon = {
                        IconButton(onClick = ::openDrawer) {
                            Icon(imageVector = Icons.Outlined.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { paddingValues ->
            LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = paddingValues) {
                items(state.couponsFromCategory) { coupon ->
                    CouponView(coupon = coupon)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeDrawer(
    categories: List<Category>,
    selectedCategory: Category?,
    onSelectCategory: (Category) -> Unit,
) {
    ModalDrawerSheet {
        Text(
            text = "Cafeteria",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp)
        )
        categories.forEach { category ->
            NavigationDrawerItem(
                icon = { Icon(category.icon(), contentDescription = null) },
                label = { Text(text = category.name) },
                selected = category == selectedCategory,
                onClick = { onSelectCategory(category) },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
            )
        }
    }
}

@Composable
private fun CouponView(coupon: Coupon) {
    Column(
        modifier = Modifier
            .clickable { }
            .padding(vertical = 6.dp),
    ) {
        Column(modifier = Modifier.height(128.dp)) {
            Text(
                text = coupon.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            )
            Text(
                text = coupon.subtitle,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            )
        }
        Text(
            text = coupon.value.format(),
            modifier = Modifier.padding(horizontal = 12.dp),
            style = MaterialTheme.typography.labelLarge,
            color = Color(0XFF00a4ff),
        )
        Divider(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(top = 12.dp, bottom = 6.dp)
        )
    }
}
