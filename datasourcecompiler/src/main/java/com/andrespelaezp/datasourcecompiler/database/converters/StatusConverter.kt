package com.andrespelaezp.datasourcecompiler.database.converters

import androidx.room.TypeConverter
import com.andrespelaezp.datasourcecompiler.data.SourceType
import com.andrespelaezp.datasourcecompiler.data.Status

internal class StatusConverter {
    @TypeConverter
    fun statusToString(value: SourceType): String =
        value.toString()

    @TypeConverter
    fun stringToStatus(string: String): Status =
        Status.valueOf(string)
}