package com.feragusper.match.data.di

import com.feragusper.match.data.datasource.InMemoryDataSource
import com.feragusper.match.data.datasource.MatchDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindMatchDataSource(
        inMemoryDataSource: InMemoryDataSource
    ): MatchDataSource
}