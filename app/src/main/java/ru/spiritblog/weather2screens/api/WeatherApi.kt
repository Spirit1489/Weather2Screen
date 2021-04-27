package ru.spiritblog.weather2screens.api

import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET

import retrofit2.http.Headers

interface WeatherApi {



    @GET(
        "daily?lat=38.5&lon=-78.5"

    )

    @Headers(
        "x-rapidapi-key:5a36fe721fmsh87a660f8bb71d9cp1a0913jsn5ba7b9d36d08",
        "x-rapidapi-host:weatherbit-v1-mashape.p.rapidapi.com"
    )



    fun fetchWeathers(): Call<JsonObject>


}