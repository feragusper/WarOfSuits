package com.feragusper.warofsuits.architecture.presentation.process

import com.feragusper.warofsuits.architecture.presentation.mvi.MVIIntent
import com.feragusper.warofsuits.architecture.presentation.mvi.MVIResult
import kotlinx.coroutines.flow.Flow

/**
 * Keep intents processing in a single place
 */
interface MVIProcessHolder<I : MVIIntent, R : MVIResult> {

    /**
     * Transform an intent into a stream of results
     */
    fun processIntent(intent: I): Flow<R>
}