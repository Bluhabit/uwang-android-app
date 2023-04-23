/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.tutorialBudget

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.AnnotationTextItem
import com.bluehabit.budgetku.android.components.TextAnnotationWithStyle
import com.bluehabit.budgetku.android.feature.tutorialBudget.components.BottomAppTutorialBudget
import com.bluehabit.budgetku.android.feature.tutorialBudget.components.FaqAnimation
import com.bluehabit.budgetku.android.feature.tutorialBudget.components.FaqQuestion

object TutorialBudget {
    const val routeName = "TutorialBudget"
}

fun NavGraphBuilder.routeTutorialBudget(
    state: ApplicationState
) {
    composable(TutorialBudget.routeName) {
        ScreenTutorialBudget(state)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun ScreenTutorialBudget(
    appState: ApplicationState
) = UIWrapper<TutorialBudgetViewModel>(appState = appState) {
    val state by uiState.collectAsState()
    var expandedFirst by remember { mutableStateOf(false) }
    var expandedSecond by remember { mutableStateOf(false) }
    LazyColumn(content = {
        item {
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.TopCenter)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.background_tutorial_budget),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopCenter)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    TopAppBar(
                        modifier = Modifier,
                        elevation = 0.dp,
                        navigationIcon = {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = "",
                                modifier = Modifier
                                    .clickable {
                                        navigateUp()
                                    },
                                tint = Color.White
                            )
                        },
                        backgroundColor = Color.Transparent,
                        title = {
                            Text(
                                text = stringResource(R.string.title_tutorial_budget),
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        },
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_person_tutorial_budget),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 20.dp,
                                start = 42.dp,
                                end = 53.dp,
                                bottom = 8.dp
                            )
                    )

                    Text(text = stringResource(R.string.what_is_budget), style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    TextAnnotationWithStyle(
                        labels = listOf(
                            AnnotationTextItem.Text(stringResource(R.string.desc_1), MaterialTheme.typography.subtitle1),
                            AnnotationTextItem.Text(
                                stringResource(R.string.desc_2), MaterialTheme.typography.subtitle1.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            ),
                            AnnotationTextItem.Text(
                                stringResource(R.string.desc_3),
                                MaterialTheme.typography.subtitle1
                            )
                        )
                    )
                    Spacer(modifier = Modifier.height(35.dp))

                    FaqQuestion(
                        onExpanded = {
                            expandedFirst = !it
                        },
                        title = stringResource(R.string.how_to_create_budget),
                        isExpand = expandedFirst
                    )

                    FaqAnimation(isExpanded = expandedFirst) {
                        Text(
                            text = stringResource(id = R.string.lorem_ipsum),
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    FaqQuestion(
                        onExpanded = {
                            expandedSecond = !it
                        },
                        title = stringResource(R.string.how_to_evaluation_budget),
                        isExpand = expandedSecond
                    )

                    FaqAnimation(isExpanded = expandedSecond) {
                        Text(
                            text = stringResource(id = R.string.lorem_ipsum),
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(28.dp))
        }
        item {
            BottomAppTutorialBudget {

            }
        }
    })
}

@Preview
@Composable
fun PreviewScreenTutorialBudget() {
    BaseMainApp {
        ScreenTutorialBudget(it)
    }
}