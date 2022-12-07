package com.feragusper.match.presentation.process

import com.feragusper.architecture.presentation.coroutines.DispatcherProvider
import com.feragusper.architecture.presentation.extensions.catchTyped
import com.feragusper.architecture.presentation.process.MVIProcessHolder
import com.feragusper.match.domain.usecase.CreateMatchUseCase
import com.feragusper.match.domain.usecase.NextRoundUseCase
import com.feragusper.match.presentation.error.MatchException
import com.feragusper.match.presentation.mvi.MatchIntent
import com.feragusper.match.presentation.mvi.MatchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MatchProcessHolder @Inject constructor(
    private val createMatchUseCase: CreateMatchUseCase,
    private val nextRoundUseCase: NextRoundUseCase,
    private val dispatcherProvider: DispatcherProvider
) : MVIProcessHolder<MatchIntent, MatchResult> {

    override fun processIntent(intent: MatchIntent): Flow<MatchResult> =
        when (intent) {
            MatchIntent.Open -> processOpen()
            MatchIntent.NextRound -> processNextRound()
        }

    private fun processNextRound(): Flow<MatchResult> = flow<MatchResult> {
        emit(MatchResult.MatchUpdated(nextRoundUseCase.execute()))
    }.catchTyped(MatchException::class) { emit(MatchResult.Failure) }.flowOn(dispatcherProvider.default())

    private fun processOpen(): Flow<MatchResult> = flow<MatchResult> {
        createMatchUseCase.execute()
        emit(MatchResult.MatchCreated)
    }.catchTyped(MatchException::class) { emit(MatchResult.Failure) }.flowOn(dispatcherProvider.default())


}
