package com.facil.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facil.notes.R
import com.facil.notes.framework.BaseFragment

class NoteEditorFragment: BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val noteId = arguments?.getString("noteId")
        val view = inflater.inflate(R.layout.fragment_note_editor, container, false)
        view.findViewById<TextView>(R.id.tv_title).text = noteId

        return view
    }
}