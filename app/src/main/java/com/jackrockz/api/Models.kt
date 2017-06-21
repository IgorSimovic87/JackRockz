package com.jackrockz.api

// Authentication Token Model
class AuthenticationTokenModel (
        val authentication_token: TokenModel
)
class TokenModel (
        val token: String
)

//City Model
class CitiesModel(
        val cities: List<CityModel>
)
class CityModel(
        val id: Int,
        val name: String,
        val phone: String,
        val email: String,
        val image: HashImage?
)
class HashImage(
        val default: String,
        val square: String,
        val medium: String,
        val large: String
)


//Event Model
class EventsModel(
        val events: List<EventModel>
)
class EventModel(
        val id: Int,
        val title: String,
        val subtitle: String?,
        val description: String,
        val guestlist_count: Int,
        val views_count: Int,
        val experience_count: Int,
        val image: HashImage?,
        val price: String,
        val regular_price: String,
        val venue: VenueModel,
        val start_date: String,
        val end_date: String
)
class VenueModel(
        val name: String,
        val city: String
)


//Ambassador Model
class AmbassadorsModel(
        val ambassador: AmbassadorModel
)
class AmbassadorModel(
        val id: Int,
        val first_name: String,
        val last_name: String,
        val code: String
)


//User Model
class UserModel(
        val id: Int,
        val first_name: String?,
        val last_name: String?,
        val email: String?,
        val locale: String,
        val country: String,
        val arrival_date: String?,
        val departure_date: String?,
        val city: CityModel?,
        val ambassador: AmbassadorModel?
)
class UsersModel(
        val user: UserModel
)

