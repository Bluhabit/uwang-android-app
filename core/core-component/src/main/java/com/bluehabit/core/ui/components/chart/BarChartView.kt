/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.chart

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter

/**
 * Bar chart
 * author Trian Damai
 * created_at 15/02/22 - 21.57
 * site https://trian.app
 */
@Composable
fun BarChartView(
    modifier: Modifier = Modifier,
    title: String = "",
    maxAxis: Float = 5f,
    items: List<BarEntry> = listOf(),
    labels: List<String> = listOf()
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    Column(
        modifier = modifier
            .width(currentWidth)
            .height(currentWidth - 30.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.surface)
            .padding(
                vertical = 16.dp
            )

    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(horizontal = 18.dp)
        )
        AndroidView(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            factory = {
                RoundedBarChart(it).apply {
                    axisRight.apply {
                        setDrawGridLines(false)
                        setDrawAxisLine(false)
                        setDrawLabels(false)

                        valueFormatter = YAxisValueFormatter()
                        textColor =  Color.DKGRAY
                    }
                    axisLeft.apply {
                        setDrawGridLines(false)
                        setDrawAxisLine(false)
                        setDrawLabels(false)

                        spaceTop = 4f
                        valueFormatter = YAxisValueFormatter()
                        textColor =  Color.DKGRAY
                    }
                    xAxis.apply {
                        axisMaximum = 7f
                        setDrawLabels(true)
                        setDrawGridLines(false)
                        setDrawAxisLine(true)
                        position = XAxis.XAxisPosition.BOTTOM
                        textColor = Color.DKGRAY
                    }
                    setVisibleYRangeMaximum(
                        maxAxis,
                        YAxis.AxisDependency.LEFT
                    )

                    legend.apply {
                        textColor = Color.DKGRAY
                    }
                    description.apply {
                        textColor =  Color.DKGRAY
                    }

                }
            },
            update = { chart ->
                chart.setRadius(30)
                if (items.isNotEmpty()) {
                    chart.xAxis.valueFormatter = XAxisTimeFormatter(labels)
                    chart.data = BarData(listOf(BarDataSet(items, "Tudu"))).apply {
                        setValueTextColor(Color.DKGRAY)
                    }
                    chart.invalidate()
                }
            }
        )
    }

}

class XAxisTimeFormatter(data: List<String>?) : ValueFormatter() {
    private val getCurrent: MutableList<String> = ArrayList()
    override fun getAxisLabel(value: Float, axis: AxisBase): String {
        return if (getCurrent.size <= value.toInt()) "" else getCurrent[value.toInt()]
    }

    override fun getFormattedValue(value: Float): String {
        return "${value.toInt()}"
    }

    init {
        getCurrent.addAll(data!!)
    }
}

class YAxisValueFormatter() : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return "${value.toInt()}"
    }

    override fun getFormattedValue(value: Float): String {
        return "${value.toInt()}"
    }
}