# MyNotes App 

<p align="center">
  <img src="myNote.png" alt="AppLogo" width="200"/>
</p>

This repository contains  "MyNotes" Android application. The project is a simple yet polished note-taking application built entirely with modern Android development tools, including Kotlin and Jetpack Compose.

The primary focus of this project was to deliver a high-quality, functional, and visually appealing application that provides a great user experience. Emphasis has been placed on creating a bug-free app with a clean UI and solid local database functionality.

## 📥 Download

You can download the latest version of the app from the official releases page.

[**Download the latest `myNotes.apk` here**](https://github.com/AvinashKhichar/MyNotes/releases/latest)

On the releases page, scroll down to the **Assets** section to find and download the `.apk` file.

## ✅ Features

### User Interface (Jetpack Compose)

- [x] **Main Screen:** A main screen that displays a list of all notes saved by the user.
- [x] **Add Note Button:** A Floating Action Button (FAB) is provided to create a new note.
- [x] **Add/Edit Screen:** A dedicated screen for both adding new notes and editing existing ones.
- [x] **Modern UI Design:** The UI is designed to be clean, modern, and user-friendly, following Material Design principles.

### Firebase Authentication
- [x] **Firebase Integration:** The project is configured and integrated with a Firebase backend.
- [x] **Sign-In:** Implements a complete user sign-in and sign-up flow using Firebase Authentication with the Sign-In provider.
- [x] **Protected Routes:** The main features of the app (viewing and managing notes) are only accessible after a user has successfully signed in.
- [x] **Sign Out Option:** A clear and accessible "Sign Out" option is available within the app.

### Data Persistence (Room Database) 
- [x] **Local Storage:** Uses the Room persistence library to store all notes locally on the device.
- [x] **Note Structure:** Each note entity includes a **title**, **content**, and a **timestamp** indicating when it was last modified. 


## 🛠️ Tech Stack & Architecture

- **Language:** Kotlin
- **UI Toolkit:** Jetpack Compose
- **Architecture:** MVVM (Model-View-ViewModel)
- **Asynchronous Programming:** Kotlin Coroutines
- **Database:** Room Persistence Library
- **Authentication:** Firebase Authentication
- **Navigation:** Jetpack Navigation for Compose
- **UI Components:** Material 3

## 🚀 Setup and Installation

To build and run the application on your local machine, please follow these steps.

1.  **Clone the Repository**
    ```bash
    git clone https://github.com/AvinashKhichar/MyNotes
    ```

2.  **Firebase Setup**
    -   This project requires a `google-services.json` file to connect to the Firebase backend.
    -   Go to the [Firebase Console](https://console.firebase.google.com/), create a new project, and add an Android app with the package name `com.example.mynotes` (or your package name).
    -   Download the generated `google-services.json` file.
    -   Place the `google-services.json` file in the **`app/`** directory of the project.

3.  **Open in Android Studio**
    -   Open the project in the latest stable version of Android Studio.
    -   Let Android Studio sync the Gradle files.

4.  **Run the Application**
    -   Select an emulator or connect a physical device.
    -   Click the "Run" button to build and install the app.
