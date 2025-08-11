package com.example.mynotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(
    private val dao: NoteDAO
): ViewModel(){

    private val _sortType = MutableStateFlow(SortType.Date)
    @OptIn(ExperimentalCoroutinesApi::class)
    private val _notes = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.Date -> dao.getNotesOrderedByDate()
                SortType.ID -> dao.getNotesOrderedByID()
                SortType.TITLE -> dao.getNotesOrderedByTitle()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(NotesState())

    val state = combine(_state, _sortType, _notes) { state, sortType, notes ->
        state.copy(
            notes = notes,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NotesState())

    fun onEvent(event: NotesEvent){
        when (event){
            is NotesEvent.DeleteNotes -> {
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
            }

            NotesEvent.SaveNote -> {

                val title = state.value.title
                val description = state.value.description
                val id = state.value.id

                if (title.isBlank() || description.isBlank()) {
                    return
                }

                if (id == null) {

                    val note = Notes(
                        title = title,
                        content = description
                    )
                    viewModelScope.launch {
                        dao.insertNote(note)
                    }
                } else {

                    val note = Notes(
                        id = id,
                        title = title,
                        content = description
                    )
                    viewModelScope.launch {
                        dao.updateNote(note)
                    }
                }

                _state.update { it.copy(
                    id = null,
                    title = "",
                    description = ""
                )}



            }

            is NotesEvent.SetDescription -> {
                _state.update { it.copy(
                    description = event.description
                )}
            }
            is NotesEvent.SetTitle -> {
                _state.update { it.copy(
                    title = event.title
                )}
            }

            is NotesEvent.SortNotes ->{
                _sortType.value = event.sortType
            }

            is NotesEvent.OnAddNoteClick -> {
                _state.update { it.copy(
                    id = null,
                    title = "",
                    description = ""
                )}
            }
            is NotesEvent.OnNoteClick -> {
                _state.update { it.copy(
                    id = event.note.id,
                    title = event.note.title,
                    description = event.note.content
                )}
            }
        }
    }
}