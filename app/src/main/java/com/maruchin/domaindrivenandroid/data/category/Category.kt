package com.maruchin.domaindrivenandroid.data.category

data class Category(val id: String, val name: String)

val sampleCategories = listOf(
    Category(id = "for_home", name = "For home"),
    Category(id = "for_kid", name = "For kid"),
    Category(id = "for_pet", name = "For pet"),
    Category(id = "drugstores_and_cosmetics", name = "Drugstores and Cosmetics"),
    Category(id = "culture_and_entertainment", name = "Culture and Entertainment"),
    Category(id = "clothing_and_accessories", name = "Clothing and Accessories"),
    Category(id = "restaurants_and_catering", name = "Restaurants and Catering"),
    Category(id = "development_and_education", name = "Development and Education"),
    Category(id = "cars", name = "Cars"),
    Category(id = "shops_and_supermarkets", name = "Shops and Supermarkets"),
    Category(id = "sport", name = "Sport"),
    Category(id = "supplements", name = "Supplements"),
)
