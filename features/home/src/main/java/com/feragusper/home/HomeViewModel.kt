package com.feragusper.home

import androidx.lifecycle.ViewModel
import com.feragusper.home.navigation.HomeNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    lateinit var navigator: HomeNavigator
}