package com.feragusper.match.data.entity

data class MatchEntity(
    val currentRound: RoundEntity?,
    val score: Pair<Int, Int>,
    val finished: Boolean,
    val suitPriority: List<String>
) {
    data class RoundEntity(val turns: Pair<TurnEntity, TurnEntity>) {
        data class TurnEntity(val card: CardEntity, val won: Boolean)
    }
}
