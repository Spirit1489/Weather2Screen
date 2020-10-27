package ru.spiritblog.weather2screens.database

import androidx.room.TypeConverter
import ru.spiritblog.weather2screens.WeekTemperatures
import java.util.*

class WeatherTypeConverters {


    @TypeConverter
    fun fromInt(int: Int?): String? {
        return int.toString()

    }

    @TypeConverter
    fun toInt(int: String?): Int? {
        return int?.toInt()
    }


    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }


    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {

        return uuid?.toString()

    }


    @TypeConverter

    fun toWeekTemperatures(value: String?): WeekTemperatures {
        if (value == null || value.isEmpty()) {
            return WeekTemperatures()
        }

        val list: List<String> = value.split(",")
        val intList = ArrayList<Int>()

        for (item in list) {
            if (!item.isEmpty()) {
                intList.add(item.toInt())
            }
        }

        return WeekTemperatures(intList)

    }


    @TypeConverter

    fun fromWeekTemperature(weekTemperatures: WeekTemperatures?): String {

        var string = ""

        if (weekTemperatures == null) {
            return string

        }

        weekTemperatures.weekTemperatures.forEach {
            string += "$it,"
        }

        return string


    }


}