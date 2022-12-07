package com.feragusper.match.data.datasource

import com.feragusper.match.data.entity.MatchEntity

interface MatchDataSource {

    fun createMatch()
    fun nextRound(): MatchEntity

}