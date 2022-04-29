// AND - Labo 4
// Authors : Alessandro Parrino, Daniel Sciarra, Wilfried Karel Ngueukam Djeuda ◕◡◕
// Date: 01.05.22
package com.and_l4.room.models.converters

import androidx.room.TypeConverter
import java.util.Date
import java.util.Calendar


class CalendarConverter {
    @TypeConverter
    fun toCalendar(dateLong: Long) = Calendar.getInstance().apply {
        time = Date(dateLong)
    }

    @TypeConverter
    fun fromCalendar(date: Calendar) = date.time.time
}