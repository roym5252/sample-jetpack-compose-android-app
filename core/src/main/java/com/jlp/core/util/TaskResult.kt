package com.jlp.core.util

/**
 * Generic class for holding Task result.
 */
sealed class TaskResult<out T : Any?> {
    data class Success<out T : Any?>(val data: T?=null) : TaskResult<T>()
    data class Error(
        val errorType: ErrorType? = null,
    ) : TaskResult<Nothing>()

}