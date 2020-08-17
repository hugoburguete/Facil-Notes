package com.facil.notes.pojos

class Note {
    var id: String = ""
    var title: String = ""
    var content: String = ""
    var tag: String = ""
    var tagColour: String = ""

    override fun toString(): String {
        return super.toString() + " with ID $id"
    }
}