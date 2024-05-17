package com.catsapp.android.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Custom Application class annotated with @HiltAndroidApp.
 *
 * This class serves as the entry point for Hilt dependency injection in the application.
 * It initializes Hilt's component dependencies and provides access to them throughout the app.
 */
@HiltAndroidApp
class App : Application()