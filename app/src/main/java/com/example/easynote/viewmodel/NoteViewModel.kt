package com.example.easynote.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.easynote.database.Note
import com.example.easynote.database.NoteDatabase
import com.example.easynote.repo.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.invoke(application).noteDao()
        noteRepository = NoteRepository(dao)
        allNotes = noteRepository.allNotes

    }


    fun insertNote(note: Note) = viewModelScope.launch {
        noteRepository.insert(note)

    }
    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.delete(note)

    }
    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.update(note)

    }


}