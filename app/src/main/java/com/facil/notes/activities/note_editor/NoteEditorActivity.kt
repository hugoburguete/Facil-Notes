package com.facil.notes.activities.note_editor

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import com.facil.notes.R
import com.facil.notes.fragments.NoteEditorFragment
import com.facil.notes.framework.BaseActivity
import com.facil.notes.presenters.NoteEditorContract
import java.lang.Exception

class NoteEditorActivity : BaseActivity(), NoteEditorContract.OnNoteSavedListener,
    NoteEditorContract.OnNoteLoadFailureListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_editor)

        val noteId = intent.getStringExtra("noteId")
        val arguments = Bundle()
        arguments.putString("noteId", noteId)
        val fragment = NoteEditorFragment()
        fragment.arguments = arguments

        inflateFragment(R.id.fl_note_editor_fragment, fragment)
    }

    override fun onNoteSaved() {
        Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
    }

    override fun onNoteSaveFailure(e: Exception) {
        TODO("Not yet implemented")
    }

    override fun onNoteLoadFailure(e: Exception) {
        TODO("Not yet implemented")
    }
}
