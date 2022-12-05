package com.feragusper.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.feragusper.home.R

@Composable
fun HomeView(navigateToMatch: () -> Unit) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.home_title),
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center
            )
            Button(navigateToMatch) {
                Text(
                    text = stringResource(R.string.home_button_quick_start),
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeViewPreview() {
    HomeView {}
}