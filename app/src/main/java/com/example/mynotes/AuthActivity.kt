package com.example.mynotes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynotes.ui.theme.MyNotesTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class AuthActivity : ComponentActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().reference

        setContent {
            MyNotesTheme {
                AuthNavigation()
            }
        }
    }

    @Composable
    fun AuthNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
                LoginScreen(
                    navController = navController,
                    onLogin = { email, password ->
                        loginUser(email, password)
                    }
                )
            }
            composable("signup") {
                SignUpScreen(
                    navController = navController,
                    onSignUp = { name, email, password ->
                        signUpUser(name, email, password)
                    }
                )
            }
        }
    }

    private fun loginUser(email: String, pass: String) {
        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "Email and Password cannot be blank", Toast.LENGTH_SHORT).show()
            return
        }
        mAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    navigateToMainApp()
                } else {
                    Toast.makeText(this, "Login failed. Please check your credentials.", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun signUpUser(name: String, email: String, pass: String) {
        if (name.isBlank() || email.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }
        mAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                    navigateToMainApp()
                } else {
                    Toast.makeText(this, "Sign-up failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String) {
        mDbRef.child("user").child(uid).setValue(User(name, email, uid))
    }

    private fun navigateToMainApp() {
        val intent = Intent(this@AuthActivity, NotesActivity::class.java)
        finish()
        startActivity(intent)
    }
}