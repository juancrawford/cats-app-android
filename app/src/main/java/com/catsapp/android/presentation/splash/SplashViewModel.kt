package com.catsapp.android.presentation.splash

import androidx.lifecycle.ViewModel
import com.catsapp.android.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * ViewModel for handling splash screen logic.
 *
 * This ViewModel class is responsible for managing the logic related to the splash screen,
 * such as launching the app and handling loading states.
 */
@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    /**
     * Launches the app and emits loading and data states.
     *
     * This function emits loading state initially, then simulates a delay of 2000 milliseconds
     * before emitting the data state indicating that the app should be launched. If an exception
     * occurs during the delay, it emits an error state.
     *
     * @return A flow of State objects representing loading, data, or error states.
     * @see State
     */
    fun launchApp() = flow {
        emit(State.LoadingState())
        try {
            delay(2000)
            emit(State.DataState(true))
        } catch (e: Exception) {
            emit(State.ErrorState(e))
        }
    }
}