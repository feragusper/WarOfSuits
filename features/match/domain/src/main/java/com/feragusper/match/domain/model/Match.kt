package com.feragusper.match.domain.model

data class Match(val currentRound: Round?, val score: Pair<Int, Int>, val finished: Boolean) {
    data class Round(val turns: Pair<Turn, Turn>) {
        data class Turn(val card: Card)
    }
}