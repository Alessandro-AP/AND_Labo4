package com.and_l4.models.converters

import androidx.room.TypeConverter
import com.and_l4.models.Type

class TypeEnumConverter {
    @TypeConverter
    fun fromTypeEnum(type: Type) = type.name // ou ordinal

    @TypeConverter
    fun toTypeEnum(name: String) = Type.valueOf(name)
}