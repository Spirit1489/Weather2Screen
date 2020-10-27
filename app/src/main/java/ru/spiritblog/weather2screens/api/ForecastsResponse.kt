package ru.spiritblog.weather2screens.api

import com.google.gson.annotations.SerializedName
import ru.spiritblog.weather2screens.Weather

class ForecastsResponse {
    @SerializedName("forecasts")
    lateinit var weathers: List<Weather>

}