package com.xoliu.crossdiary.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.xoliu.crossdiary.model.entities.Note
import com.xoliu.crossdiary.model.local.dao.NoteDao
import com.xoliu.crossdiary.model.local.database.AppDatabase

class NoteRepository(val context:Application) {
    val allNotes: LiveData<List<Note>> = AppDatabase.getDatabase(context).noteDao().getAllNotes()

    suspend fun insert(note: Note) {
        AppDatabase.getDatabase(context).noteDao().insert(note)
    }
}