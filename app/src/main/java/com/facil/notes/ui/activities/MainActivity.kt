package com.facil.notes.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import com.facil.notes.R
import com.facil.notes.framework.BaseActivity
import com.facil.notes.pojos.Note
import com.facil.notes.ui.fragments.note_editor.NoteEditorContract
import com.facil.notes.ui.fragments.note_editor.NoteEditorFragment
import com.facil.notes.ui.fragments.note_list.NoteListContract
import com.facil.notes.ui.fragments.note_list.NotesListFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : BaseActivity(), NoteListContract.OnNoteSelectedListener,
    NoteEditorContract.OnNoteLoadFailureListener, NoteEditorContract.OnNoteSavedListener {

    private val notesListFragment = NotesListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        inflateFragment(R.id.fl_notes_list, notesListFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNoteSelected(note: Note) {
        val flNoteEditor = findViewById<FrameLayout>(R.id.fl_note_editor)
        if (flNoteEditor != null) {
            val noteEditorFragment =
                NoteEditorFragment()
            val bundle = Bundle()
            bundle.putInt("noteId", note.id)
            noteEditorFragment.arguments = bundle
            inflateFragment(R.id.fl_note_editor, noteEditorFragment)
        } else {
            val intent = Intent(this, NoteEditorActivity::class.java).apply {
                putExtra("noteId", note.id)
            }

            startActivity(intent)
        }
    }

    override fun onNoteLoadFailure(e: Exception) {
        TODO("Not yet implemented")
    }

    override fun onNoteSaved() {
        TODO("Not yet implemented")
    }

    override fun onNoteSaveFailure(e: Exception) {
        TODO("Not yet implemented")
    }
}
