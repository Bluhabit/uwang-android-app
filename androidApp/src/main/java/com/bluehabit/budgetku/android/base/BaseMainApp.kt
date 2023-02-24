package com.bluehabit.budgetku.android.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.rememberApplicationState
import com.bluehabit.budgetku.android.ui.NoteTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun BaseMainApp(
    appState: ApplicationState = rememberApplicationState(),
    eventListener: EventListener = EventListener(),
    topAppBar: @Composable (ApplicationState) -> Unit = {},
    bottomBar: @Composable (ApplicationState) -> Unit = {},
    snackbarBar: @Composable (ApplicationState) -> Unit = {},
    bottomSheet: @Composable (ApplicationState) -> Unit = {},
    content: @Composable (appState: ApplicationState, event: EventListener) -> Unit = { _, _ -> }
) {
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = {
            when (it) {
                ModalBottomSheetValue.Hidden -> {
                    eventListener.bottomSheetClose()
                }
                else -> Unit
            }
            true
        }
    )
    NoteTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ModalBottomSheetLayout(
                sheetContent = {
                    bottomSheet(appState)
                },
                sheetState = bottomSheetState,
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
                    },
                ) {
                    Column(
                        modifier = Modifier.padding(it)
                    ) {
                        content(appState, eventListener)
                    }
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