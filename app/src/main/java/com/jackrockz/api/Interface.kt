package com.jackrockz.api

import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface JackApi {
    @FormUrlEncoded
    @POST("users/token")
    fun postUser(@Field("facebook_access_token") facebook_access_token: String): Call<AuthenticationTokenModel>

    @FormUrlEncoded
    @PUT("users/me")
    fun putMe(@Field("country") country: String?, @Field("city_id") city:String?, @Field("arrival_date") arrivalDate: Date?, @Field("departure_data") departureDate: Date?): Call<Any>

    @GET("cities")
    fun getCities(): Call<CitiesModel>

    @GET("events")
    fun getEvents(): Call<CitiesModel>
}