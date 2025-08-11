package com.example.mynotes

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.mynotes.ui.theme.MyNotesTheme
import com.google.firebase.auth.FirebaseAuth

class NotesActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            NotesDatabase::class.java,
            "notes.db"
        ).build()
    }

    private val viewModel by viewModels<NotesViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NotesViewModel(db.dao) as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNotesTheme {
                val state by viewModel.state.collectAsState()

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "notes_screen") {

                    composable("notes_screen") {
                        NotesScreen(
                            state = state,
                            onEvent = viewModel::onEvent,
                            navController = navController,
                            onLogout = {
                                FirebaseAuth.getInstance().signOut()
                                val intent = Intent(this@NotesActivity, AuthActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                                finish()
                            }
                        )
                    }

                    composable("add_note_screen") {
                        AddNoteScreen(
                            state = state,
                            onEvent = viewModel::onEvent,
                            navController = navController
                        )
                    }

                    composable("note_detail_screen") {
                        NoteDetailScreen(
                            state = state,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}