package com.feragusper.home.presentation.mvi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feragusper.architecture.presentation.mvi.MVIViewState
import com.feragusper.design.theme.WarOfSuitsTheme
import com.feragusper.design.ui.PrimaryButton
import com.feragusper.home.R

class HomeViewState : MVIViewState<HomeIntent> {

    @Composable
    override fun Compose(intent: (HomeIntent) -> Unit) {
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.home_title),
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.secondary,
                    textAlign = TextAlign.Center
                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .padding(horizontal = 32.dp),
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                PrimaryButton(
                    onClick = { intent(HomeIntent.QuickMatch) },
                    text = stringResource(R.string.home_button_quick_start)
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeViewPreview() {
    WarOfSuitsTheme {
        HomeViewState().Compose {}
    }
}
