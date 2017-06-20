package com.jackrockz.api

import com.jackrockz.MyApplication
import com.jackrockz.utils.GlobalConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class RestApi {
    private val jackApi: JackApi
    private val jackApiHeader: JackApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(GlobalConstants.API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        jackApi = retrofit.create(JackApi::class.java)

        val builder= OkHttpClient().newBuilder()
                .addInterceptor { chain ->
                    val request = chain!!.request().newBuilder().addHeader("X-JR-Token", MyApplication.instance.accessToken).build()
                    chain.proceed(request)
                }

        val retrofitHeader = Retrofit.Builder()
                .baseUrl(GlobalConstants.API_BASE_URL)
                .client(builder.build())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        jackApiHeader = retrofitHeader.create(JackApi::class.java)

    }

    fun getToken(facebook_access_token: String) = jackApi.postUser(facebook_access_token)
    fun putMe(country: String?, city: String?, arrivalDate: Date?, departureDate: Date?) = jackApiHeader.putMe(country, city, arrivalDate, departureDate)
    fun getCities() = jackApiHeader.getCities()

}