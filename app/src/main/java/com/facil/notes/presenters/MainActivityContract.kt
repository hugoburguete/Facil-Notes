package com.facil.notes.presenters

import com.facil.notes.framework.BasePresenter
import com.facil.notes.framework.BaseView
import com.facil.notes.pojos.Note

class MainActivityContract {
    // Views
    interface View: BaseView<Presenter>,
        OnNoteSelectedListener {
        fun onNotesLoaded(notes: ArrayList<Note>)
    }

    // Presenters
    interface Presenter: BasePresenter {
        fun loadNotes()
    }

    // Events
    interface OnNoteSelectedListener {
        fun onNoteSelected(note: Note)
    }
}