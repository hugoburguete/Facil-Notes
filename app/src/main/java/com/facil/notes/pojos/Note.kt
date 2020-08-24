package com.facil.notes.pojos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "rowid") var id: Int? = null,
    var title: String = "",
    var content: String = ""
) {

    override fun toString(): String {
        return super.toString() + " with ID $id"
    }
}
