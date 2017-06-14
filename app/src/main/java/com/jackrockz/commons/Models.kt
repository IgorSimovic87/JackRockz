package com.jackrockz.commons

class CityModel(
        val id: Int,
        val name: String,
        val phone: String,
        val email: String,
        val image: ImageItem
)

class ImageItem(
        val default: String,
        val square: String,
        val medium: String,
        val large: String
)