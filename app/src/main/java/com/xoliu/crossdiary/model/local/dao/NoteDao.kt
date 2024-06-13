package com.xoliu.crossdiary.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xoliu.crossdiary.model.entities.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note): Long

    @Query("SELECT * FROM notes ORDER BY date DESC, time DESC")
    fun getAllNotes(): LiveData<List<Note>>

    // 其他数据库操作方法，如查询单个、更新、删除等
}