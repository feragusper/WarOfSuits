package com.feragusper.match.domain.mapper

import com.feragusper.match.data.entity.CardEntity
import com.feragusper.match.data.entity.MatchEntity
import com.feragusper.match.domain.model.Card
import com.feragusper.match.domain.model.Match
import javax.inject.Inject

class MatchMapper @Inject constructor() {

    fun transform(match: MatchEntity) = Match(
        currentRound = transform(match.currentRound),
        score = Pair(match.score.first, match.score.second),
        finished = match.finished,
        suitPriority = match.suitPriority
    )

    private fun transform(roundEntity: MatchEntity.RoundEntity?) = roundEntity?.let {
        Match.Round(
            turns = Pair(transform(it.turns.first), transform(it.turns.second))
        )
    }

    private fun transform(turn: MatchEntity.RoundEntity.TurnEntity) = Match.Round.Turn(transform(turn.card), turn.won)

    private fun transform(cardEntity: CardEntity) = Card(cardEntity.value, cardEntity.suit)
}
