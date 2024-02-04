/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.sheet

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.ext.Empty
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.CustomTypography
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import java.time.LocalDate
import kotlinx.coroutines.launch

@Composable
fun DatePickerBottomSheet(
    modifier: Modifier=Modifier,
    title: String = String.Empty,
    value:LocalDate?= null,
    minDate: LocalDate = LocalDate.MIN,
    maxDate: LocalDate = LocalDate.MAX,
    onDone: () -> Unit = {},
    onClose: () -> Unit = {},
    onChange: (date: LocalDate) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(
                Color.White,
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp
                )
            )
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                lineHeight = 28.sp,
                color = UwangColors.Neutral.Grey9,
                style = CustomTypography.Body.XL.W600
            )
            IconButton(
                onClick = onClose
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "close",
                    tint = Color.Black,
                )
            }
        }
        WheelDatePicker(
            modifier = Modifier
                .fillMaxWidth(),
            startDate = value ?: LocalDate.now(),
            minDate = minDate,
            maxDate=maxDate,
            size = DpSize(321.dp, 264.dp),
            rowCount = 5,
            textStyle = CustomTypography.Body.Large.W600,
            textColor = Color(0xFF212121),
            selectorProperties = WheelPickerDefaults.selectorProperties(
                enabled = false,
            ),
            onSnappedDate = { snappedDate ->
                onChange.invoke(snappedDate)
            }
        )
        ButtonPrimary(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.btn_text_save),
            onClick = onDone
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview(widthDp = 500, heightDp = 750, backgroundColor = 0xffffff)
fun DatePickerBottomSheetPreview() {
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true,

        )
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetBackgroundColor = Color.White,
        sheetShape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp
        ),
        sheetContent = {
            DatePickerBottomSheet(
                title = "Title Here",
                onClose = {
                    scope.launch {
                        modalSheetState.hide()
                    }
                }
            )
        }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color(0x1A0D0D0D)),
        ) {
            Button(onClick = { scope.launch { modalSheetState.show() } }) {
                Text("Click to show sheet")
            }
        }

    }
}