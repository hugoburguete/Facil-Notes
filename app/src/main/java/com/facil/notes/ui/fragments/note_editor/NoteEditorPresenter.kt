package com.facil.notes.ui.fragments.note_editor

import android.util.Log
import com.facil.notes.pojos.Note
import com.facil.notes.pojos.Tag
import com.facil.notes.repositories.NoteRepositoryContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteEditorPresenter(
    private val view: NoteEditorContract.View,
    private val notesRepository: NoteRepositoryContract
): NoteEditorContract.Presenter {
    val LOG_TAG = "FC/NoteEditorPresenter"

    /**
     * Retrieves and returns a specific note
     */
    override suspend fun loadNote(noteId: Int) {
        println("Loading note $noteId")

        try {
            val note = notesRepository.getNote(noteId)
//            println("Note ${note.id} retrieved")
            withContext(Dispatchers.Main) {
                view.onNoteLoaded(note)
            }
        } catch (error: Exception) {
            view.onNoteLoadFailure(error)
        }
    }

    /**
     * Saves a new note
     */
    override fun saveNote(note: Note, tags: List<Tag>) {
        Log.d(LOG_TAG, "Saving note titled ${note.title}")
        GlobalScope.launch {
            try {
                notesRepository.saveNote(note, tags)

                println("Saved note ${note.id}")
                withContext(Dispatchers.Main) {
                    view.onNoteSaved()
                }
            } catch (error: Exception) {
                view.onNoteSaveFailure(error)
            }
        }
    }
}