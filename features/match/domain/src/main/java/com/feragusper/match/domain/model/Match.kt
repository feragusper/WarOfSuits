package com.feragusper.match.domain.model

data class Match(val currentRound: Round?, val score: Pair<Int, Int>, val finished: Boolean, val suitPriority: List<String>) {
    data class Round(val turns: Pair<Turn, Turn>) {
        data class Turn(val card: Card, val won: Boolean)
    }
}