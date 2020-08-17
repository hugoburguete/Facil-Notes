package com.facil.notes.ui.fragments.note_list

import com.facil.notes.framework.BasePresenter
import com.facil.notes.framework.BaseView
import com.facil.notes.pojos.Note

class NoteListContract {
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