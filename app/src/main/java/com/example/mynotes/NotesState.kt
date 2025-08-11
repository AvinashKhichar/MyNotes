package com.example.mynotes

data class NotesState (
    val notes: List<Notes> = emptyList(),
    val title: String = "",
    val description: String = "",
    val sortType: SortType = SortType.Date,
    val id: Int? = null
)
