package com.feragusper.match.data.datasource

import com.feragusper.match.data.entity.CardEntity
import com.feragusper.match.data.entity.DeckEntity
import com.feragusper.match.data.entity.MatchEntity
import com.feragusper.match.data.entity.MatchEntity.RoundEntity
import com.feragusper.match.data.entity.MatchEntity.RoundEntity.TurnEntity
import javax.inject.Inject

class MatchProcessor @Inject constructor() {

    private lateinit var currentMatchState: MatchState

    val matchStateEntity
        get() = currentMatchState.toMatchEntity()

    fun nextRound() {
        when (currentMatchState.state) {
            MatchState.State.ON_GOING -> with(currentMatchState) {
                if (players.first.deck.isNotEmpty()) {
                    val firstPlayerCard = players.first.deck.first()
                    val secondPlayerCard = players.second.deck.first()
                    resolveRound(firstPlayerCard, secondPlayerCard)
                    currentRound = RoundEntity(TurnEntity(firstPlayerCard) to TurnEntity(secondPlayerCard))
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
        currentMatchState.players.first.score += if (first > second) 1 else 0
        currentMatchState.players.second.score += if (second > first) 1 else 0
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
            finished = state == State.FINISHED
        )

        data class Player(
            val deck: MutableList<CardEntity>,
            var score: Int = 0
        )
    }
}