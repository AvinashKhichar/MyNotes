package com.example.mynotes

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mynotes.ui.theme.MyNotesTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyNotesTheme {
                SplashScreen()
            }
        }
    }

    @Composable
    private fun SplashScreen() {
        LaunchedEffect(key1 = true) {
            delay(2000L)

            val user = FirebaseAuth.getInstance().currentUser
            val intent = if (user != null) {
                Intent(this@SplashActivity, NotesActivity::class.java)
            } else {

                Intent(this@SplashActivity, AuthActivity::class.java)
            }
            startActivity(intent)
            finish()
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.mynote),
                contentDescription = "App Logo",
                modifier = Modifier.size(150.dp)
            )
        }
    }
}