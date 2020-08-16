package com.facil.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.facil.notes.R
import com.facil.notes.activities.note_editor.NoteEditorPresenter
import com.facil.notes.framework.BaseFragment
import com.facil.notes.pojos.Note
import com.facil.notes.presenters.NoteEditorContract
import com.facil.notes.repositories.NotesRepository
import kotlinx.android.synthetic.main.fragment_note_editor.*

class NoteEditorFragment : BaseFragment(), NoteEditorContract.View {
    private val presenter: NoteEditorPresenter = NoteEditorPresenter(this, NotesRepository())
    var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val noteId = arguments?.getString("noteId")
        if (noteId == null) {
            onNoteLoadFailure(Exception("Invalid NoteId"))
        } else {
            presenter.loadNote(noteId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_editor, container, false)
    }

    fun saveNote() {
        val n = note;
        if (n is Note) {
            n.title = etTitle.text.toString()
            n.content = etNoteContent.text.toString()
            presenter.saveNote(n)
        }
    }

    override fun onNoteLoaded(note: Note) {
        this.note = note;
        val rootView = view
        if (rootView is View) {
            // Set the note content
            etTitle.setText(note.title)
            etNoteContent.setText(note.content)

            // Save note handler
            rootView.findViewById<Button>(R.id.btn_save_note)
                .setOnClickListener {
                    saveNote()
                }
        }
    }

    override fun onNoteLoadFailure(e: Exception) {
        (activity as NoteEditorContract.OnNoteLoadFailureListener).onNoteLoadFailure(e)
    }

    override fun onNoteSaved() {
        (activity as NoteEditorContract.OnNoteSavedListener).onNoteSaved()
    }

    override fun onNoteSaveFailure(e: Exception) {
        (activity as NoteEditorContract.OnNoteSavedListener).onNoteSaveFailure(e)
    }
}