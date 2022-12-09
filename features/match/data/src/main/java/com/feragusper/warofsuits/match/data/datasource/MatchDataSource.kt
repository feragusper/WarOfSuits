package com.feragusper.warofsuits.match.data.datasource

import com.feragusper.warofsuits.match.data.entity.MatchEntity

interface MatchDataSource {

    fun createMatch(): MatchEntity
    fun nextRound(): MatchEntity

}