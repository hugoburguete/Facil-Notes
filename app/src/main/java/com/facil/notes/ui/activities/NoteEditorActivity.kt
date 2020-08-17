package com.facil.notes.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.facil.notes.R
import com.facil.notes.framework.BaseActivity
import com.facil.notes.ui.fragments.note_editor.NoteEditorContract
import com.facil.notes.ui.fragments.note_editor.NoteEditorFragment

class NoteEditorActivity : BaseActivity(), NoteEditorContract.OnNoteSavedListener,
    NoteEditorContract.OnNoteLoadFailureListener {
    private val fragment = NoteEditorFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_editor)

        val noteId = intent.getStringExtra("noteId")
        val arguments = Bundle()
        arguments.putString("noteId", noteId)
        fragment.arguments = arguments

        inflateFragment(R.id.fl_note_editor_fragment, fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.note_editor, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                fragment.saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
