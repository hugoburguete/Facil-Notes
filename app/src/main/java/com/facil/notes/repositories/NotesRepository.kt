package com.facil.notes.repositories

import com.facil.notes.framework.BaseRepository
import com.facil.notes.pojos.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

interface NoteRepositoryContract {
    suspend fun getNotes(): ArrayList<Note>

    suspend fun getNote(noteId: String): Note
}

class NotesRepository: BaseRepository(), NoteRepositoryContract {
    override suspend fun getNotes(): ArrayList<Note> {
        return withContext(Dispatchers.IO) {
            val noteList = ArrayList<Note>()
            var i = 0
            while (i < 20) {
                val note = Note()
                note.id = i.toString()
                note.title = "Note $i"
                noteList.add(note)
                i++
            }
            noteList
        }
    }

    override suspend fun getNote(noteId: String): Note {
        return withContext(Dispatchers.IO) {
            val note = Note()
            note.id = noteId
            note.title = "Note $noteId"
            note
        }
    }
}