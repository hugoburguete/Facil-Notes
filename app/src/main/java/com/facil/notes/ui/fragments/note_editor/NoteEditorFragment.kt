package com.facil.notes.ui.fragments.note_editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.facil.notes.R
import com.facil.notes.framework.BaseActivity
import com.facil.notes.framework.BaseFragment
import com.facil.notes.pojos.Note
import com.facil.notes.pojos.Tag
import com.facil.notes.repositories.NotesRepository
import kotlinx.android.synthetic.main.fragment_note_editor.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteEditorFragment : BaseFragment(), NoteEditorContract.View {
    private var presenter: NoteEditorPresenter? = null
    var note: Note? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            return
        }

        val appDatabase = (activity as BaseActivity).getDatabase()
        presenter = NoteEditorPresenter(this, NotesRepository(appDatabase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val noteId = arguments?.getInt("noteId")
        if (noteId == null) {
            // onNoteLoadFailure(Exception("Invalid NoteId"))
        } else {
            GlobalScope.launch {
                presenter?.loadNote(noteId)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_editor, container, false)
        view.findViewById<Button>(R.id.btn_save_note)
            .setOnClickListener {
                println("saving note")
                saveNote()
            }
        return view
    }

    fun saveNote() {
        var n = note
        if (n == null) {
            n = Note()
        }

        n.title = etTitle.text.toString()
        n.content = etNoteContent.text.toString()
        val dummyTag = Tag(name = "Untagged", colour = "#00FF00")
        presenter?.saveNote(n, listOf(dummyTag))
    }

    override fun onNoteLoaded(note: Note) {
        this.note = note
        val rootView = view
        if (rootView is View) {
            // Set the note content
            etTitle.setText(note.title)
            etNoteContent.setText(note.content)
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