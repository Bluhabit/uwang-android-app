/*
 * Copyright (c) 2023 trian.app.
 */

package app.hilwa.ar.base.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.isSimple
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * `Extension for colored shadow in card`
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 04/09/2021
 */
@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.coloredShadow(
    color: Color,
    alpha: Float = 0.2f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 20.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = composed {

    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparent = color.copy(alpha= 0f).toArgb()

    this.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = transparent

            frameworkPaint.setShadowLayer(
                shadowRadius.toPx(),
                offsetX.toPx(),
                offsetY.toPx(),
                shadowColor
            )
            it.drawRoundRect(
                0f,
                0f,
                this.size.width,
                this.size.height,
                borderRadius.toPx(),
                borderRadius.toPx(),
                paint
            )
        }
    }
}

/**
 * Modify element to add border with appearance specified with a [border] and a [shape], pad the
 * content by the [BorderStroke.width] and clip it.
 *
 * @sample androidx.compose.foundation.samples.BorderSample()
 *
 * @param border [BorderStroke] class that specifies border appearance, such as size and color
 * @param shape shape of the border
 */
fun Modifier.dashedBorder(border: BorderStroke, shape: Shape = RectangleShape, on: Dp, off: Dp) =
    dashedBorder(width = border.width, brush = border.brush, shape = shape, on, off)

/**
 * Returns a [Modifier] that adds border with appearance specified with [width], [color] and a
 * [shape], pads the content by the [width] and clips it.
 *
 * @sample androidx.compose.foundation.samples.BorderSampleWithDataClass()
 *
 * @param width width of the border. Use [Dp.Hairline] for a hairline border.
 * @param color color to paint the border with
 * @param shape shape of the border
 * @param on the size of the solid part of the dashes
 * @param off the size of the space between dashes
 */
fun Modifier.dashedBorder(width: Dp, color: Color, shape: Shape = RectangleShape, on: Dp, off: Dp) =
    dashedBorder(width, SolidColor(color), shape, on, off)

/**
 * Returns a [Modifier] that adds border with appearance specified with [width], [brush] and a
 * [shape], pads the content by the [width] and clips it.
 *
 * @sample androidx.compose.foundation.samples.BorderSampleWithBrush()
 *
 * @param width width of the border. Use [Dp.Hairline] for a hairline border.
 * @param brush brush to paint the border with
 * @param shape shape of the border
 */
fun Modifier.dashedBorder(width: Dp, brush: Brush, shape: Shape, on: Dp, off: Dp): Modifier =
    composed(
        factory = {
            this.then(
                Modifier.drawWithCache {
                    val outline: Outline = shape.createOutline(size, layoutDirection, this)
                    val borderSize = if (width == Dp.Hairline) 1f else width.toPx()

                    var insetOutline: Outline? = null // outline used for roundrect/generic shapes
                    var stroke: Stroke? = null // stroke to draw border for all outline types
                    var pathClip: Path? = null // path to clip roundrect/generic shapes
                    var inset = 0f // inset to translate before drawing the inset outline
                    // path to draw generic shapes or roundrects with different corner radii
                    var insetPath: Path? = null
                    if (borderSize > 0 && size.minDimension > 0f) {
                        if (outline is Outline.Rectangle) {
                            stroke = Stroke(
                                borderSize, pathEffect = PathEffect.dashPathEffect(
                                    floatArrayOf(on.toPx(), off.toPx())
                                )
                            )
                        } else {
                            // Multiplier to apply to the border size to get a stroke width that is
                            // large enough to cover the corners while not being too large to overly
                            // square off the internal shape. The resultant shape will be
                            // clipped to the desired shape. Any value lower will show artifacts in
                            // the corners of shapes. A value too large will always square off
                            // the internal shape corners. For example, for a rounded rect border
                            // a large multiplier will always have squared off edges within the
                            // inner section of the stroke, however, having a smaller multiplier
                            // will still keep the rounded effect for the inner section of the
                            // border
                            val strokeWidth = 1.2f * borderSize
                            inset = borderSize - strokeWidth / 2
                            val insetSize = Size(
                                size.width - inset * 2,
                                size.height - inset * 2
                            )
                            insetOutline = shape.createOutline(insetSize, layoutDirection, this)
                            stroke = Stroke(
                                strokeWidth, pathEffect = PathEffect.dashPathEffect(
                                    floatArrayOf(on.toPx(), off.toPx())
                                )
                            )
                            pathClip = if (outline is Outline.Rounded) {
                                Path().apply { addRoundRect(outline.roundRect) }
                            } else if (outline is Outline.Generic) {
                                outline.path
                            } else {
                                // should not get here because we check for Outline.Rectangle
                                // above
                                null
                            }

                            insetPath =
                                if (insetOutline is Outline.Rounded &&
                                    !insetOutline.roundRect.isSimple
                                ) {
                                    // Rounded rect with non equal corner radii needs a path
                                    // to be pre-translated
                                    Path().apply {
                                        addRoundRect(insetOutline.roundRect)
                                        translate(Offset(inset, inset))
                                    }
                                } else if (insetOutline is Outline.Generic) {
                                    // Generic paths must be created and pre-translated
                                    Path().apply {
                                        addPath(insetOutline.path, Offset(inset, inset))
                                    }
                                } else {
                                    // Drawing a round rect with equal corner radii without
                                    // usage of a path
                                    null
                                }
                        }
                    }

                    onDrawWithContent {
                        drawContent()
                        // Only draw the border if a have a valid stroke parameter. If we have
                        // an invalid border size we will just draw the content
                        if (stroke != null) {
                            if (insetOutline != null && pathClip != null) {
                                val isSimpleRoundRect = insetOutline is Outline.Rounded &&
                                        insetOutline.roundRect.isSimple
                                withTransform({
                                    clipPath(pathClip)
                                    // we are drawing the round rect not as a path so we must
                                    // translate ourselves othe
                                    if (isSimpleRoundRect) {
                                        translate(inset, inset)
                                    }
                                }) {
                                    if (isSimpleRoundRect) {
                                        // If we don't have an insetPath then we are drawing
                                        // a simple round rect with the corner radii all identical
                                        val rrect = (insetOutline as Outline.Rounded).roundRect
                                        drawRoundRect(
                                            brush = brush,
                                            topLeft = Offset(rrect.left, rrect.top),
                                            size = Size(rrect.width, rrect.height),
                                            cornerRadius = rrect.topLeftCornerRadius,
                                            style = stroke
                                        )
                                    } else if (insetPath != null) {
                                        drawPath(insetPath, brush, style = stroke)
                                    }
                                }
                                // Clip rect to ensure the stroke does not extend the bounds
                                // of the composable.
                                clipRect {
                                    // Draw a hairline stroke to cover up non-anti-aliased pixels
                                    // generated from the clip
                                    if (isSimpleRoundRect) {
                                        val rrect = (outline as Outline.Rounded).roundRect
                                        drawRoundRect(
                                            brush = brush,
                                            topLeft = Offset(rrect.left, rrect.top),
                                            size = Size(rrect.width, rrect.height),
                                            cornerRadius = rrect.topLeftCornerRadius,
                                            style = Stroke(
                                                Stroke.HairlineWidth,
                                                pathEffect = PathEffect.dashPathEffect(
                                                    floatArrayOf(on.toPx(), off.toPx())
                                                )
                                            )
                                        )
                                    } else {
                                        drawPath(
                                            pathClip, brush = brush, style = Stroke(
                                                Stroke.HairlineWidth,
                                                pathEffect = PathEffect.dashPathEffect(
                                                    floatArrayOf(on.toPx(), off.toPx())
                                                )
                                            )
                                        )
                                    }
                                }
                            } else {
                                // Rectangular border fast path
                                val strokeWidth = stroke.width
                                val halfStrokeWidth = strokeWidth / 2
                                drawRect(
                                    brush = brush,
                                    topLeft = Offset(halfStrokeWidth, halfStrokeWidth),
                                    size = Size(
                                        size.width - strokeWidth,
                                        size.height - strokeWidth
                                    ),
                                    style = stroke
                                )
                            }
                        }
                    }
                }
            )
        },
        inspectorInfo = debugInspectorInfo {
            name = "border"
            properties["width"] = width
            if (brush is SolidColor) {
                properties["color"] = brush.value
                value = brush.value
            } else {
                properties["brush"] = brush
            }
            properties["shape"] = shape
        }
    )


/**
 * Media query for Dp multiple screen
 * created by Rahman Ecky R
 *
 **/


@Composable
fun Dp.from(
    context: Context,
    defaultScreenWidth: Double = 375.0,
    defaultScreenHeight: Double = 812.0
): Dp {

    val screenWidth = context
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density

    val screenHeight = context
        .resources
        .displayMetrics.heightPixels.dp /
            LocalDensity.current.density

    val a = screenHeight.value.toDouble().pow(2.0)
    val b = screenWidth.value.toDouble().pow(2.0)
    val currentDiagonal = sqrt(a + b).dp
    //now design using size with w = 375 h = 812
    val displayMetrics = DisplayMetrics()
    val defWidth = defaultScreenWidth.pow(2)
    val defHeight = defaultScreenHeight.pow(2)
    val defScreenDiagonal = sqrt(defWidth + defHeight)

    val resultCompare = currentDiagonal.value / defScreenDiagonal

    val result = (this.value * resultCompare).toInt()

    return result.dp

}

/**
 * Media query for text unit multiple screen
 * created by Rahman Ecky R
 *
 **/

@Composable
fun TextUnit.from(
    context: Context,
    defaultScreenWidth: Double = 375.0,
    defaultScreenHeight: Double = 812.0
): TextUnit {
    val screenWidth = context
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density

    val screenHeight = context
        .resources
        .displayMetrics.heightPixels.dp /
            LocalDensity.current.density

    val a = screenHeight.value.toDouble().pow(2.0)
    val b = screenWidth.value.toDouble().pow(2.0)
    val currentDiagonal = sqrt(a + b).dp

    //now design using default size with w = 375 h = 812
    val defWidth = defaultScreenWidth.pow(2)
    val defHeight = defaultScreenHeight.pow(2)
    val defScreenDiagonal = sqrt(defWidth + defHeight)

    val resultCompare = currentDiagonal.value / defScreenDiagonal
    val result = (this.value * resultCompare).toInt()

    return result.sp

}

/**
 * Dp to Pixel
 *
 **/

@Composable
fun Dp.toPixel(
    context: Context,
): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.from(
            context
        ).value,
        context.resources.displayMetrics
    )
}

/**
 * Sp to Pixel
 *
 **/

@Composable
fun TextUnit.toPixel(
    context: Context,
): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.from(context).value,
        context.resources.displayMetrics
    )
}