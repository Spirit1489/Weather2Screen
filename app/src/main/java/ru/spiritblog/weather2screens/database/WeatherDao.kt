package ru.spiritblog.weather2screens.database

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.spiritblog.weather2screens.models.Weather
import java.util.*

@Dao
interface WeatherDao {


    @Query("SELECT * FROM weather")
    fun getWeathers(): LiveData<List<Weather>>


    @Query("SELECT * FROM weather WHERE id=(:id)")
    fun getWeather(id: UUID): LiveData<Weather?>


    @Update
    fun updateWeather(weather: Weather)

    @Insert
    fun addWeather(weather: Weather)

    @Delete
    fun deleteWeather(weather: Weather)


}