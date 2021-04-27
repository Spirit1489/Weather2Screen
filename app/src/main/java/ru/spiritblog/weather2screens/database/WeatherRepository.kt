package ru.spiritblog.weather2screens.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.google.gson.JsonObject
import ru.spiritblog.weather2screens.api.WeatherFetchr
import ru.spiritblog.weather2screens.models.Weather
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors


private const val DATABASE_NAME = "weather-database"


class WeatherRepository private constructor(context: Context) {


    private val database: WeatherDatabase = Room.databaseBuilder(
        context.applicationContext,
        WeatherDatabase::class.java,
        DATABASE_NAME

    ).build()


    private val weatherDao = database.weatherDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getWeathers(): LiveData<List<Weather>> = weatherDao.getWeathers()

    fun getWeather(id: UUID): LiveData<Weather?> = weatherDao.getWeather(id)


    fun updateWeather(weather: Weather) {
        executor.execute {
            weatherDao.updateWeather(weather)
        }
    }


    fun addWeather(weather: Weather) {
        executor.execute {
            weatherDao.addWeather(weather)
        }
    }


//
//    val weatherLiveData: LiveData<JsonObject> = WeatherFetchr().fetchWeathers()
//
//    weatherLiveData.observe(this, Observer { weathers ->
//        Log.d(TAG, "Response received: $weathers")
//
//    })
//
//







    companion object {

        private var INSTANCE: WeatherRepository? = null


        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = WeatherRepository(context)
            }

        }


        fun get(): WeatherRepository {
            return INSTANCE ?: throw IllegalStateException("WeatherRepository must be initialize")


        }


    }


}


