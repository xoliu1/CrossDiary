package com.xoliu.crossdiary.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xoliu.crossdiary.model.entities.Converters
import com.xoliu.crossdiary.model.entities.Note
import com.xoliu.crossdiary.model.local.dao.NoteDao

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "noteDatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}