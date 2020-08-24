package com.facil.notes.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import com.facil.notes.R
import com.facil.notes.framework.BaseActivity
import com.facil.notes.pojos.NoteWithTags
import com.facil.notes.ui.fragments.note_editor.NoteEditorContract
import com.facil.notes.ui.fragments.note_editor.NoteEditorFragment
import com.facil.notes.ui.fragments.note_list.NoteListContract
import com.facil.notes.ui.fragments.note_list.NotesListFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : BaseActivity(), NoteListContract.OnNoteSelectedListener,
    NoteEditorContract.OnNoteLoadFailureListener, NoteEditorContract.OnNoteSavedListener {

    private val notesListFragment = NotesListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, NoteEditorActivity::class.java)
            startActivity(intent)
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

    override fun onNoteSelected(noteWithTags: NoteWithTags) {
        val note = noteWithTags.note
        val flNoteEditor = findViewById<FrameLayout>(R.id.fl_note_editor)
        if (flNoteEditor != null) {
            val noteEditorFragment =
                NoteEditorFragment()
            val bundle = Bundle()
            note.id?.let { bundle.putInt("noteId", it) }
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
        println("failed to load notes")
        println(e)
    }

    override fun onNoteSaved() {
        println("note saved")
    }

    override fun onNoteSaveFailure(e: Exception) {
        println("note not saved")
    }
}
