# Kreeda-Ankana (Sports) Android App

Kreeda-Ankana is an Android application designed as a "Ground & Match Organizer" for village sports grounds. It digitizes the village notice board, allowing local teams to book ground time slots, challenge other teams, and share match results with the community.

## Features
*   **Ground Calendar & Booking**: View available time slots and reserve a slot for your team. Prevents double-booking conflicts.
*   **Challenge Board**: A real-time notice board to post challenges ("Our team is ready for a Volleyball match this Sunday") and accept open challenges.
*   **Score Wall**: Post and view the latest match results from around the village.

## Architecture
*   **Language**: Kotlin
*   **UI Framework**: Jetpack Compose (Modern, responsive design with dark mode styling).
*   **Local DB**: Room Database.
*   **Architecture**: MVVM.

## Installation & Setup Instructions

To run and build this application from the source code, follow these steps:

### Prerequisites
*   [Android Studio](https://developer.android.com/studio) installed on your computer.

### Step-by-Step Guide
1.  **Clone or Download the Repository**: 
    If you haven't already, clone this repository to your local machine:
    ```bash
    git clone https://github.com/sachinkn92/kreeda-ankana
    ```
2.  **Open in Android Studio**:
    *   Launch Android Studio.
    *   Select **File > Open...**
    *   Navigate to the folder containing this repository and select it.
3.  **Sync Gradle**:
    *   Android Studio will automatically detect the `build.gradle.kts` files and begin downloading dependencies. Wait for the process to complete (you'll see a green checkmark or "Sync successful" at the bottom).
4.  **Run on Emulator / Device**:
    *   Connect your Android device via USB (ensure USB Debugging is enabled) or start an Android Virtual Device (AVD).
    *   Click the green **Play (Run)** button in the top toolbar to install and run the app.

### Building the `.apk`
If you want to generate a standalone `.apk` file to share with others:
1.  In Android Studio, click on **Build** in the top menu.
2.  Select **Build Bundle(s) / APK(s) > Build APK(s)**.
3.  Once the build finishes, a notification will appear in the bottom right corner. Click **locate** to open the folder containing your compiled `app-debug.apk`. You can transfer this file to any Android phone to install it.
