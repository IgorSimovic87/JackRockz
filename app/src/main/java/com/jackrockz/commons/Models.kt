package com.jackrockz.commons

import java.util.*

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

class EventModel(
        val id: Int,
        val date: Date,
        val target_country: String
)
