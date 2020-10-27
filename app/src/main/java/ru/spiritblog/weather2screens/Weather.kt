package ru.spiritblog.weather2screens

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList


@Entity

data class Weather(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var cityTitle: String = "",        // Город задается пользователем
    var cityDescription: String = "", // Подробная информация из сервиса
    var date: String = "",
    var temperature: Int = 0,
    var description: String = "",
    var weekTemperatures: WeekTemperatures? = WeekTemperatures(),
//    var weekDescriptions: WeekDescriptions?


)

data class WeekTemperatures(
    var weekTemperatures: ArrayList<Int> = ArrayList()
)
//
//data class WeekDescriptions(
//    var weekDescriptions: ArrayList<String> = ArrayList()
//)











