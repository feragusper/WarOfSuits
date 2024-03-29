package com.feragusper.warofsuits.match.presentation.error

/**
 * Domain errors expected in the business logic
 */
class MatchException(message: String, cause: Throwable? = null) : Exception(message, cause)