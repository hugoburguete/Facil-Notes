package com.facil.notes.repositories

import com.facil.notes.database.AppDatabase
import com.facil.notes.framework.BaseRepository
import com.facil.notes.pojos.Note
import com.facil.notes.pojos.NoteTagRelation
import com.facil.notes.pojos.NoteWithTags
import com.facil.notes.pojos.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface NoteRepositoryContract {
    suspend fun getNotes(): List<NoteWithTags>

    suspend fun getNotesBySearchTerm(searchTerm: String): List<NoteWithTags>

    suspend fun getNote(noteId: Int): Note

    suspend fun saveNote(note: Note, tags: List<Tag>)
}

class NotesRepository(private val appDatabase: AppDatabase) : BaseRepository(),
    NoteRepositoryContract {
    override suspend fun getNotes(): List<NoteWithTags> {
        return withContext(Dispatchers.IO) {
            appDatabase.noteDao().getAll()
        }
    }

    /**
     * Retrieves notes with titles / descriptions matching the search term
     */
    override suspend fun getNotesBySearchTerm(searchTerm: String): List<NoteWithTags> {
        return withContext(Dispatchers.IO) {
            appDatabase.noteDao().getAll()
        }
    }

    override suspend fun getNote(noteId: Int): Note {
        return withContext(Dispatchers.IO) {
            appDatabase.noteDao().findById(noteId)
        }
    }

    override suspend fun saveNote(note: Note, tags: List<Tag>) {
        return withContext(Dispatchers.IO) {
            val noteId = appDatabase.noteDao().insert(note)
            val tagIds = appDatabase.tagsDao().insertAll(*tags.toTypedArray())
            tagIds.forEach { tagId ->
                val relation = NoteTagRelation(noteId, tagId)
                appDatabase.noteTagRelationDao().insert(relation)
            }
        }
    }
}