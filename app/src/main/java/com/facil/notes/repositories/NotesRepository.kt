package com.facil.notes.repositories

import com.facil.notes.framework.BaseRepository
import com.facil.notes.pojos.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface NoteRepositoryContract {
    suspend fun getNotes(): ArrayList<Note>

    suspend fun getNotesBySearchTerm(searchTerm: String): ArrayList<Note>

    suspend fun getNote(noteId: String): Note

    suspend fun saveNote(note: Note)
}

class NotesRepository : BaseRepository(), NoteRepositoryContract {
    override suspend fun getNotes(): ArrayList<Note> {
        // TODO: Implement this
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

    /**
     * Retrieves notes with titles / descriptions matching the search term
     */
    override suspend fun getNotesBySearchTerm(searchTerm: String): ArrayList<Note> {
        // TODO: Implement this
        return withContext(Dispatchers.IO) {
            val noteList = ArrayList<Note>()
            var i = 0
            while (i < 10) {
                val note = Note()
                note.id = "Note $i"
                note.title = "Note search result $i"
                noteList.add(note)
                i++
            }
            noteList
        }
    }

    override suspend fun getNote(noteId: String): Note {
        // TODO: Implement this
        return withContext(Dispatchers.IO) {
            val note = Note()
            note.id = noteId
            note.title = "Note $noteId"
            note.content = "some content"
            delay(200)
            note
        }
    }

    override suspend fun saveNote(note: Note) {
        // TODO: Implement this
    }
}