package com.example.easynote.repo

import androidx.lifecycle.LiveData
import com.example.easynote.database.Note
import com.example.easynote.database.NoteDao

class NoteRepository(private val noteDao: NoteDao) {


    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    suspend fun deleteAll() {
        noteDao.deleteAll()
    }


}