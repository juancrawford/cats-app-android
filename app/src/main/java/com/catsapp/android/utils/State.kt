package com.catsapp.android.utils

/**
 * A sealed class representing the state of a data operation.
 *
 * This class encapsulates the different states that a data operation can be in, such as loading,
 * error, or data.
 *
 * @param T The type of data held by the DataState.
 */
sealed class State<out T> {
    /**
     * Represents a loading state, optionally containing a message.
     *
     * @property message An optional message describing the loading state.
     */
    data class LoadingState(var message: String? = null) : State<Nothing>()

    /**
     * Represents an error state, containing an exception.
     *
     * @property exception The exception that caused the error state.
     */
    data class ErrorState(var exception: Throwable) : State<Nothing>()

    /**
     * Represents a data state, containing the data.
     *
     * @param T The type of data held by this state.
     * @property data The data held by this state.
     */
    data class DataState<T>(var data: T) : State<T>()
}
