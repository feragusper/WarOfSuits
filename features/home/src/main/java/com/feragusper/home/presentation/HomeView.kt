package com.feragusper.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeView() {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            Text(
                text = "Hello world!"
            )
        }
    }
}