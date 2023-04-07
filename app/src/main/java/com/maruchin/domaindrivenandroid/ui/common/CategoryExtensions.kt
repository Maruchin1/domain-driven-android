package com.maruchin.domaindrivenandroid.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Checkroom
import androidx.compose.material.icons.outlined.ChildFriendly
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.HealthAndSafety
import androidx.compose.material.icons.outlined.House
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Sports
import androidx.compose.material.icons.outlined.TheaterComedy
import androidx.compose.ui.graphics.vector.ImageVector
import com.maruchin.domaindrivenandroid.data.category.Category

fun Category.icon(): ImageVector {
    return when (id) {
        "for_home" -> Icons.Outlined.House
        "for_kid" -> Icons.Outlined.ChildFriendly
        "for_pet" -> Icons.Outlined.Pets
        "drugstores_and_cosmetics" -> Icons.Outlined.Category
        "culture_and_entertainment" -> Icons.Outlined.TheaterComedy
        "clothing_and_accessories" -> Icons.Outlined.Checkroom
        "restaurants_and_catering" -> Icons.Outlined.Restaurant
        "development_and_education" -> Icons.Outlined.School
        "cars" -> Icons.Outlined.DirectionsCar
        "shops_and_supermarkets" -> Icons.Outlined.ShoppingCart
        "sport" -> Icons.Outlined.Sports
        "supplements" -> Icons.Outlined.HealthAndSafety
        else -> Icons.Outlined.Category
    }
}
