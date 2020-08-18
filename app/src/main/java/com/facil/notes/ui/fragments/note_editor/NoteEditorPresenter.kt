package com.facil.notes.ui.fragments.note_editor

import com.facil.notes.pojos.Note
import com.facil.notes.repositories.NoteRepositoryContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteEditorPresenter(
    private val view: NoteEditorContract.View,
    private val notesRepository: NoteRepositoryContract
): NoteEditorContract.Presenter {
    /**
     * Retrieves and returns a specific note
     */
    override suspend fun loadNote(noteId: String) {
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
    override fun saveNote(note: Note) {
        println("Saving note ${note.id}")
        GlobalScope.launch {
            try {
                notesRepository.saveNote(note)

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