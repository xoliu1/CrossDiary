package com.xoliu.crossdiary.model.entities

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler
import java.time.LocalDate
import java.time.LocalTime
import kotlinx.parcelize.IgnoredOnParcel
import java.time.format.DateTimeFormatter

// 定义 LocalDateParceler和 LocalTimeParceler 来处理 LocalDate 和 LocalTime 的序列化和反序列化
@Parcelize
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: LocalDate,
    val weekDay: String,
    val time: LocalTime,
    val contentPreview: String,
    val fullContent: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = createdAt
) : Parcelable

class Converters {
    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let { LocalDate.parse(it) }
    }

    @TypeConverter
    fun fromLocalTime(time: LocalTime?): String? {
        return time?.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalTime(timeString: String?): LocalTime? {
        return timeString?.let { LocalTime.parse(it) }
    }
}