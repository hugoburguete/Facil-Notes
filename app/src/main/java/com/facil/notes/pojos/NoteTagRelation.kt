package com.facil.notes.pojos

import androidx.room.*

@Entity(
    tableName = "note_tag_relation",
    primaryKeys = ["note_id", "tag_id"]
)
data class NoteTagRelation(
    @ColumnInfo(name = "note_id") val noteId: Long,
    @ColumnInfo(name = "tag_id") val tagId: Long
)

data class NoteWithTags(
    @Embedded val note: Note,
    @Relation(
        parentColumn = "rowid",
        entityColumn = "id",
        associateBy = Junction(
            NoteTagRelation::class,
            parentColumn = "note_id",
            entityColumn = "tag_id"
        )
    ) val tags: List<Tag>
)

data class TagWithNotes(
    @Relation(
        parentColumn = "id",
        entityColumn = "rowid",
        associateBy = Junction(
            NoteTagRelation::class,
            parentColumn = "tag_id",
            entityColumn = "note_id"
        )
    ) val notes: List<Note>,
    @Embedded val tag: Tag
)
