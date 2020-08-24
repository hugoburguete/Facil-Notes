package com.facil.notes.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.facil.notes.pojos.Note
import com.facil.notes.pojos.NoteWithTags

@Dao
interface NotesDao {
    @Query("SELECT * from notes")
    fun getAll(): List<NoteWithTags>

    @Query("SELECT * from notes WHERE rowid = :id")
    fun findById(id: Int): Note

    @Insert
    fun insert(note: Note): Long

    @Delete
    fun delete(note: Note)
}