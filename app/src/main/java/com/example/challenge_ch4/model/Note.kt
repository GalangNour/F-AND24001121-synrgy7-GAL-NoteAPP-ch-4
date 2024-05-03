package com.example.challenge_ch4.model



import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId: Long? = null,

    @ColumnInfo(name = "note_title")
    val noteTitle: String,

    @ColumnInfo(name = "note_content")
    val  noteContent: String,

    @ColumnInfo(name = "note_creator")
    val noteCreator : String

)

