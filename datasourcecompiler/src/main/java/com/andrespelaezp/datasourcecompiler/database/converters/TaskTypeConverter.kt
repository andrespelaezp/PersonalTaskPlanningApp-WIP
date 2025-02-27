package com.andrespelaezp.datasourcecompiler.database.converters

import androidx.room.TypeConverter
import com.andrespelaezp.datasourcecompiler.data.SourceType

internal class TaskTypeConverter {
    @TypeConverter
    fun typeToString(value: SourceType): String =
        value.toString()

    @TypeConverter
    fun stringToType(string: String): SourceType =
        SourceType.valueOf(string)
}