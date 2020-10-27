package ru.spiritblog.weather2screens.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import ru.spiritblog.weather2screens.Weather
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


