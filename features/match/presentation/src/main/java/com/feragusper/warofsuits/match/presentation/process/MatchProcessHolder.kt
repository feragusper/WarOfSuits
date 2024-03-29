package com.feragusper.warofsuits.match.presentation.process

import com.feragusper.warofsuits.architecture.presentation.coroutines.DispatcherProvider
import com.feragusper.warofsuits.architecture.presentation.extensions.catchTyped
import com.feragusper.warofsuits.architecture.presentation.process.MVIProcessHolder
import com.feragusper.warofsuits.match.domain.usecase.CreateMatchUseCase
import com.feragusper.warofsuits.match.domain.usecase.NextRoundUseCase
import com.feragusper.warofsuits.match.presentation.error.MatchException
import com.feragusper.warofsuits.match.presentation.mvi.MatchIntent
import com.feragusper.warofsuits.match.presentation.mvi.MatchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MatchProcessHolder @Inject constructor(
    private val createMatchUseCase: CreateMatchUseCase,
    private val nextRoundUseCase: NextRoundUseCase,
    private val dispatcherProvider: DispatcherProvider
) : MVIProcessHolder<MatchIntent, MatchResult> {

    override fun processIntent(intent: MatchIntent): Flow<MatchResult> =
        when (intent) {
            MatchIntent.NewMatch -> processNewMatch()
            MatchIntent.NextRound -> processNextRound()
            MatchIntent.Exit -> processExit()
        }

    private fun processNextRound(): Flow<MatchResult> = flow<MatchResult> {
        emit(MatchResult.MatchUpdated(nextRoundUseCase.execute()))
    }.catchTyped(MatchException::class) { emit(MatchResult.Failure) }.flowOn(dispatcherProvider.default())

    private fun processNewMatch(): Flow<MatchResult> = flow<MatchResult> {
        emit(MatchResult.MatchCreated(createMatchUseCase.execute()))
    }.catchTyped(MatchException::class) { emit(MatchResult.Failure) }.flowOn(dispatcherProvider.default())

    private fun processExit(): Flow<MatchResult> = flowOf(MatchResult.NavigateUp)

}
