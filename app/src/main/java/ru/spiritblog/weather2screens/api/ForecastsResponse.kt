package ru.spiritblog.weather2screens.api

import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject
import ru.spiritblog.weather2screens.models.Weather

class ForecastsResponse {
    @SerializedName("data")
    lateinit var weathers: JSONArray

}