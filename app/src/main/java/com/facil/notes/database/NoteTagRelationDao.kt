package com.facil.notes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.facil.notes.pojos.Note
import com.facil.notes.pojos.NoteTagRelation
import com.facil.notes.pojos.Tag

@Dao
interface NoteTagRelationDao {
    @Insert
    fun insert(userRepoJoin: NoteTagRelation?)

    @Query(
        "SELECT * FROM notes INNER JOIN note_tag_relation ON notes.rowid = note_tag_relation.note_id WHERE note_tag_relation.note_id = :noteId"
    )
    fun getTagsForNote(noteId: Int): List<Note?>?

    @Query(
        "SELECT * FROM tags INNER JOIN note_tag_relation ON tags.id = note_tag_relation.tag_id WHERE note_tag_relation.tag_id=:tagId"
    )
    fun getNotesForTag(tagId: Int): List<Tag?>?
}