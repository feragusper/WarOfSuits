package com.feragusper.warofsuits.match.data.repository

import com.feragusper.warofsuits.match.data.datasource.MatchDataSource
import javax.inject.Inject

class MatchRepository @Inject constructor(private val matchDataSource: MatchDataSource) {

    fun createMatch() = matchDataSource.createMatch()

    fun nextRound() = matchDataSource.nextRound()

}