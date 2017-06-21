package com.jackrockz.api

import rx.Observable
import java.util.*

class ApiManager(private val api: RestApi = RestApi()) {

    fun getToken(facebook_access_token: String): Observable<String> {
        return Observable.create {
            subscriber ->
            val callResponse = api.getToken(facebook_access_token)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val token = response.body().authentication_token.token

                subscriber.onNext(token)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

    fun putMe(country: String? = null, city: String? = null, arrivalDate: Date? = null, departureDate: Date? = null, ambassadorID: String? = null): Observable<UserModel> {
        return Observable.create {
            subscriber ->
            val callResponse = api.putMe(country, city, arrivalDate, departureDate, ambassadorID)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val user = response.body().user

                subscriber.onNext(user)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

    fun getCities(): Observable<List<CityModel>> {
        return Observable.create {
            subscriber ->
            val callResponse = api.getCities()
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val listCities = response.body().cities

                subscriber.onNext(listCities)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

    fun getEvents(id: Int, date: Date): Observable<List<EventModel>> {
        return Observable.create {
            subscriber ->
            val callResponse = api.getEvents(id, date)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val listEvents = response.body().events

                subscriber.onNext(listEvents)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

    fun getAmbassador(code: String): Observable<AmbassadorModel> {
        return Observable.create {
            subscriber ->
            val callResponse = api.getAmbassadors(code)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val ambassador = response.body().ambassador

                subscriber.onNext(ambassador)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}