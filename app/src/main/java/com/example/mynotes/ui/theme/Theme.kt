package com.example.mynotes.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// 1. Your complete custom Light Theme
private val LightColorScheme = lightColorScheme(
    primary = NotesYellow,
    secondary = NotesPencilOrange,
    background = NotesBackground,
    surface = NotesBackground,
    onPrimary = NotesText,
    onBackground = NotesText,
    onSurface = NotesText,
)

// 2. A new, fully defined Dark Theme to match
private val DarkColorScheme = darkColorScheme(
    primary = NotesYellow,
    secondary = NotesPencilOrange,
    background = NotesDarkBackground,
    surface = NotesDarkBackground,
    onPrimary = NotesText,
    onSecondary = NotesText,
    onBackground = NotesCreamText,
    onSurface = NotesCreamText,
)

@Composable
fun MyNotesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // 3. The colorScheme now cleanly switches between your two custom themes
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    // 4. This block handles the status bar color for a more immersive look
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}