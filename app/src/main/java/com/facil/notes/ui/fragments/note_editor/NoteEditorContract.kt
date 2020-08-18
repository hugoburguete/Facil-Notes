package com.facil.notes.ui.fragments.note_editor

import com.facil.notes.framework.BasePresenter
import com.facil.notes.framework.BaseView
import com.facil.notes.pojos.Note

class NoteEditorContract {
    interface Presenter: BasePresenter {
        suspend fun loadNote(noteId: Int)

        fun saveNote(note: Note)
    }

    interface View: BaseView<Presenter>,
        OnNoteSavedListener,
        OnNoteLoadFailureListener {
        fun onNoteLoaded(note: Note)
    }

    // Events
    interface OnNoteLoadFailureListener {
        fun onNoteLoadFailure(e: Exception)
    }

    interface OnNoteSavedListener {
        fun onNoteSaved()
        fun onNoteSaveFailure(e: Exception)
    }
}

