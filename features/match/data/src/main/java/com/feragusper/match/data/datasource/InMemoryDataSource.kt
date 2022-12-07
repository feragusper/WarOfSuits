package com.feragusper.match.data.datasource

import com.feragusper.match.data.entity.CardEntity
import com.feragusper.match.data.entity.DeckEntity
import com.feragusper.match.data.entity.MatchEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryDataSource @Inject constructor(private val matchProcessor: MatchProcessor) : MatchDataSource {

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
        matchProcessor.newMatch(newDeck.dealCards(), suits.shuffled())
    }

    override fun nextRound(): MatchEntity {
        matchProcessor.nextRound()
        return matchProcessor.matchStateEntity
    }

    private fun List<CardEntity>.dealCards(): Pair<DeckEntity, DeckEntity> =
        shuffled()
            .mapIndexed { index, value -> index to value }.toMutableList()
            .partition { it.first >= size / 2 }
            .let { (deck1, deck2) ->
                deck1.map { it.second } to deck2.map { it.second }
            }

}

