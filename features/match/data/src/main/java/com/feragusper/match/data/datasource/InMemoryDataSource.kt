package com.feragusper.match.data.datasource

import com.feragusper.match.data.entity.CardEntity
import com.feragusper.match.data.entity.DeckEntity
import com.feragusper.match.data.entity.MatchEntity
import com.feragusper.match.data.entity.MatchEntity.RoundEntity
import com.feragusper.match.data.entity.MatchEntity.RoundEntity.TurnEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryDataSource @Inject constructor() : MatchDataSource {

    private val matchProcessor = MatchProcessor()

    private val suits = listOf(
        "spades",
        "hearts",
        "diamonds",
        "clubs"
    )

    private val newDeck by lazy {
        buildList {
            (suits.forEach { suit ->
                addAll((1..13).map {
                    CardEntity(it, suit)
                })
            })
        }
    }

    override fun createMatch() {
        val decks = newDeck.dealCards()
        matchProcessor.newMatch(decks)
    }

    override fun nextRound(): MatchEntity {
        matchProcessor.nextRound()
        return matchProcessor.currentMatchState.toMatchEntity()
    }

    private fun List<CardEntity>.dealCards(): Pair<DeckEntity, DeckEntity> =
        shuffled()
            .mapIndexed { index, value -> index to value }.toMutableList()
            .partition { it.first >= size / 2 }
            .let { (deck1, deck2) ->
                deck1.map { it.second } to deck2.map { it.second }
            }

    private class MatchState(
        val players: Pair<Player, Player>,
        var currentRound: RoundEntity? = null,
        var state: State = State.ON_GOING
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
            val score: Int = 0
        )
    }

    private class MatchProcessor {

        lateinit var currentMatchState: MatchState

        fun nextRound() {
            when (currentMatchState.state) {
                MatchState.State.ON_GOING -> with(currentMatchState) {
                    if (players.first.deck.isNotEmpty()) {
                        currentRound = RoundEntity(TurnEntity(players.first.deck.first()) to TurnEntity(players.second.deck.first()))
                        players.first.deck.removeFirst()
                        players.second.deck.removeFirst()
                    } else {
                        currentMatchState.state = MatchState.State.FINISHED
                    }
                }
                else -> throw IllegalStateException("can't do a next round with state ${currentMatchState.state}")
            }
        }

        fun newMatch(decks: Pair<DeckEntity, DeckEntity>) {
            currentMatchState = MatchState(
                players = MatchState.Player(decks.first.toMutableList()) to MatchState.Player(decks.second.toMutableList())
            )
        }
    }
}

