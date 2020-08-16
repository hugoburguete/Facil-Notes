package com.facil.notes.presenters

import com.facil.notes.framework.BasePresenter
import com.facil.notes.framework.BaseView
import com.facil.notes.pojos.Note
import java.lang.Exception

class NoteEditorContract {
    interface Presenter: BasePresenter {
        fun loadNote(noteId: String)

        fun saveNote(note: Note)
    }

    interface View: BaseView<Presenter>, OnNoteSavedListener, OnNoteLoadFailureListener {
        fun onNoteLoaded(note: Note)
    }

    interface OnNoteLoadFailureListener {
        fun onNoteLoadFailure(e: Exception)
    }

    // Events
    interface OnNoteSavedListener {
        fun onNoteSaved()
        fun onNoteSaveFailure(e: Exception)
    }
}

