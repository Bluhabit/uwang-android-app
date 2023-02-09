package com.bluehabit.budgetku.android.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.trian.learnkmm.android.ApplicationState
import app.trian.learnkmm.android.rememberApplicationState
import app.trian.learnkmm.android.ui.NoteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseMainApp(
    appState: ApplicationState = rememberApplicationState(),
    topAppBar: @Composable (ApplicationState) -> Unit = {

    },
    bottomBar: @Composable (ApplicationState) -> Unit = {

    },
    snackbarBar: @Composable (ApplicationState) -> Unit = {

    },
    content: @Composable (appState: ApplicationState) -> Unit = { }
) {
    NoteTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    topAppBar(appState)
                },
                bottomBar = {
                    bottomBar(appState)
                },
                snackbarHost = {
                    snackbarBar(appState)
                }
            ) {
                Column(
                    modifier = Modifier.padding(it)
                ) {
                    content(appState)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewBaseMainApp() {
    BaseMainApp()
}