// AND - Labo 4
// Authors : Alessandro Parrino, Daniel Sciarra, Wilfried Karel Ngueukam Djeuda ◕◡◕
// Date: 01.05.22
package com.and_l4.room.models.converters

import androidx.room.TypeConverter
import com.and_l4.room.models.Type

class TypeEnumConverter {
    @TypeConverter
    fun fromTypeEnum(type: Type) = type.name

    @TypeConverter
    fun toTypeEnum(name: String) = Type.valueOf(name)
}