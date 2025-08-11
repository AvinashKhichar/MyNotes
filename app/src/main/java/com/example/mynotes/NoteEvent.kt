package com.example.mynotes

sealed interface NotesEvent {
    object SaveNote : NotesEvent
    data class SetTitle(val title: String) : NotesEvent
    data class SetDescription(val description: String) : NotesEvent
    data class SortNotes(val sortType: SortType) : NotesEvent
    data class DeleteNotes(val note: Notes) : NotesEvent
    data class OnNoteClick(val note: Notes): NotesEvent
    object OnAddNoteClick: NotesEvent

}