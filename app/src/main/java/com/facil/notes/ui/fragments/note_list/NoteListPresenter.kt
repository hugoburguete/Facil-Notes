package com.facil.notes.ui.fragments.note_list

import com.facil.notes.framework.BasePresenter
import com.facil.notes.repositories.NoteRepositoryContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class NoteListPresenter(
    private val view: NoteListContract.View,
    private val notesRepository: NoteRepositoryContract
): BasePresenter, NoteListContract.Presenter {
    override fun loadNotes() {
        GlobalScope.launch {
            try {
                val notesList = notesRepository.getNotes()
                withContext(Dispatchers.Main) {
                    view.onNotesLoaded(notesList)
                }
            } catch (error: Exception) {
                println(error.message)
            }
        }
    }
}