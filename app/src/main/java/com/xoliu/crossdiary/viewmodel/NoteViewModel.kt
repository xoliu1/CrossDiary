package com.xoliu.crossdiary.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xoliu.crossdiary.model.entities.Note
import com.xoliu.crossdiary.model.local.database.AppDatabase
import com.xoliu.crossdiary.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel() : ViewModel() {
    private lateinit var repository: NoteRepository
    private lateinit var allNotes: LiveData<List<Note>>

    fun getNoteDao(context:Context): LiveData<List<Note>> {
        return AppDatabase.getDatabase(context).noteDao().getAllNotes()
    }
//    fun insert(note: Note, application: Application) = viewModelScope.launch {
//        val notesDao = AppDatabase.getDatabase(application).noteDao()
//        repository = NoteRepository(notesDao)
//        allNotes = repository.allNotes
//        repository.insert(note)
//    }
}