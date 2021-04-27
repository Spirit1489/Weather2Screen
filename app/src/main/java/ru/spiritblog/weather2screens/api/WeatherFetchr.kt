package ru.spiritblog.weather2screens.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.spiritblog.weather2screens.models.Weather

private const val TAG = "WeatherFetchr"


class WeatherFetchr {

    private val weatherApi: WeatherApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://weatherbit-v1-mashape.p.rapidapi.com/forecast/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        weatherApi = retrofit.create(WeatherApi::class.java)

    }


    fun fetchTestWeathers():LiveData<Weather>{


        return MutableLiveData()
    }






    fun fetchWeathers(): LiveData<JsonObject> {

        val responseLiveData: MutableLiveData<JsonObject> = MutableLiveData()
        val weatherRequest: Call<JsonObject> = weatherApi.fetchWeathers()


        weatherRequest.enqueue(object : Callback<JsonObject> {

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e(TAG, "Failed to fetch weathers", t)
            }


            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {
                Log.d(TAG, "Response received ${response.body()}")

                responseLiveData.value = response.body()
            }

        })

        return responseLiveData

    }


}