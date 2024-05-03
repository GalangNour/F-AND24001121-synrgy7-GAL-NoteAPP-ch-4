package com.example.challenge_ch4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge_ch4.database.NoteDao
import com.example.challenge_ch4.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel (private val noteDao: NoteDao) : ViewModel(){

    private val _selectedNote = MutableLiveData<Note?>()
    val selectedNote: LiveData<Note?> = _selectedNote

    fun addNote(note: Note) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                noteDao.insert(note)
            }
        }
    }
    fun selectNote(note: Note) {
        _selectedNote.value = note
    }

    fun getNotes(): LiveData<List<Note>> {
        return noteDao.getAllNote()
    }

    fun getNotesByCreator(username : String) : LiveData<List<Note>>{
        return noteDao.getNoteByCreator(username)
    }

    fun updateNote(updatedNote: Note) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                noteDao.update(updatedNote)
            }
        }
    }

    fun deleteNote(note:Note){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                noteDao.delete(note)
            }
        }
    }

}