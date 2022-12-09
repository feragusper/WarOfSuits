package com.feragusper.warofsuits.match.data.datasource

import com.feragusper.warofsuits.match.data.entity.CardEntity
import com.feragusper.warofsuits.match.data.entity.DeckEntity
import com.feragusper.warofsuits.match.data.entity.MatchEntity
import com.feragusper.warofsuits.match.data.entity.MatchEntity.RoundEntity
import com.feragusper.warofsuits.match.data.entity.MatchEntity.RoundEntity.TurnEntity
import javax.inject.Inject

class MatchProcessor @Inject constructor() {

    private lateinit var currentMatchState: MatchState

    val matchStateEntity
        get() = currentMatchState.toMatchEntity()

    fun nextRound() {
        when (currentMatchState.state) {
            MatchState.State.ON_GOING -> with(currentMatchState) {
                if (players.first.deck.isNotEmpty()) {
                    resolveRound(players.first.deck.first(), players.second.deck.first())
                    players.first.deck.removeFirst()
                    players.second.deck.removeFirst()
                } else {
                    currentMatchState.state = MatchState.State.FINISHED
                }
            }
            else -> throw IllegalStateException("can't do a next round with state ${currentMatchState.state}")
        }
    }

    private fun resolveRound(firstPlayerCard: CardEntity, secondPlayerCard: CardEntity) {
        val (first, second) = when (firstPlayerCard.value) {
            secondPlayerCard.value -> {
                val priority = currentMatchState.suitPriority
                val firstCardIndex = priority.indexOf(firstPlayerCard.suit)
                val secondCardIndex = priority.indexOf(secondPlayerCard.suit)
                firstCardIndex to secondCardIndex
            }
            else -> {
                firstPlayerCard.value to secondPlayerCard.value
            }
        }
        val firstPlayerWon = first > second
        if (firstPlayerWon) {
            currentMatchState.players.first.score++
        } else {
            currentMatchState.players.second.score++
        }
        currentMatchState.currentRound = RoundEntity(TurnEntity(firstPlayerCard, firstPlayerWon) to TurnEntity(secondPlayerCard, !firstPlayerWon))
    }

    fun newMatch(decks: Pair<DeckEntity, DeckEntity>, suitPriority: List<String>) {
        currentMatchState = MatchState(
            players = MatchState.Player(decks.first.toMutableList()) to MatchState.Player(decks.second.toMutableList()),
            suitPriority = suitPriority
        )
    }

    class MatchState(
        val players: Pair<Player, Player>,
        var currentRound: RoundEntity? = null,
        var state: State = State.ON_GOING,
        val suitPriority: List<String>
    ) {
        enum class State {
            ON_GOING,
            FINISHED
        }

        fun toMatchEntity(): MatchEntity = MatchEntity(
            currentRound = currentRound,
            score = players.first.score to players.second.score,
            finished = state == State.FINISHED,
            suitPriority = suitPriority
        )

        data class Player(
            val deck: MutableList<CardEntity>,
            var score: Int = 0
        )
    }
}