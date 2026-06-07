package com.littlelemon.adamson

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork (
    @SerialName("menu")
    val items: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: String,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String
){

fun toMenuItemRoom() = MenuItemRoom(
    id = id,
    title = title,
    description = description,
    price = price.toDoubleOrNull() ?: 0.0,
    image = image,
    category = category
)
//   @Entity
//   data class MenuItemRoom(
//       @PrimaryKey val id: Int,
//        val title: String,
//       val price: Double,
//  )


}

