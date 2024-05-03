package com.example.challenge_ch4.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.challenge_ch4.model.Note


@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Query("SELECT * FROM note_table")
    fun getAllNote() : LiveData<List<Note>>

    @Query("SELECT * FROM note_table WHERE note_creator = :noteId")
    fun getNoteByCreator(noteId: String) : LiveData<List<Note>>

    @Query("DELETE FROM note_table")
    fun clear()

    @Query("DELETE FROM note_table WHERE noteId = :noteId")
    fun deleteNoteById(noteId: Long)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)
}