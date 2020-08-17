package com.facil.notes.ui.fragments.note_list

import com.facil.notes.framework.BasePresenter
import com.facil.notes.repositories.NoteRepositoryContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteListPresenter(
    private val view: NoteListContract.View,
    private val notesRepository: NoteRepositoryContract
): BasePresenter, NoteListContract.Presenter {
    override fun loadNotes() {
        view.onNotesLoading()
        GlobalScope.launch {
            try {
                val notesList = notesRepository.getNotes()
                withContext(Dispatchers.Main) {
                    view.onNotesLoaded(notesList)
                }
            } catch (error: Exception) {
                view.onNotesLoadFailure(error)
            }
        }
    }

    override fun searchNotes(searchTerm: String) {
        view.onNotesLoading()
        // TODO: Implement this
        GlobalScope.launch {
            try {
                val notesList = notesRepository.getNotesBySearchTerm(searchTerm)
                withContext(Dispatchers.Main) {
                    view.onNotesFound(notesList)
                }
            } catch (error: Exception) {
                view.onNotesSearchFailure(error)
            }
        }
    }
}