package com.facil.notes.ui.fragments.note_list

import com.facil.notes.framework.BasePresenter
import com.facil.notes.framework.BaseView
import com.facil.notes.pojos.Note

class NoteListContract {
    // Views
    interface View : BaseView<Presenter>,
        OnNoteSelectedListener {
        fun onNotesLoading()
        fun onNotesLoaded(notes: ArrayList<Note>)
        fun onNotesLoadFailure(e: Exception)

        fun onNotesFound(notes: ArrayList<Note>)
        fun onNotesSearchFailure(e: Exception)
    }

    // Presenters
    interface Presenter : BasePresenter {
        fun loadNotes()
        fun searchNotes(searchTerm: String)
    }

    // Events
    interface OnNoteSelectedListener {
        fun onNoteSelected(note: Note)
    }
}