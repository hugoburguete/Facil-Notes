package com.facil.notes.pojos

import androidx.room.*

@Fts4
@Entity(
    tableName = "notes",
    indices = [Index(value = ["title", "content"])]
)
data class Note(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "rowid") var id: Int,
    var title: String = "",
    var content: String = "",
    @Embedded var tag: Tag?,
    @ColumnInfo(name = "tag_colour") var tagColour: String = ""
) {

    override fun toString(): String {
        return super.toString() + " with ID $id"
    }
}