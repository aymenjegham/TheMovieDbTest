package io.dvlt.themoviedbtest.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.dvlt.themoviedbtest.R
import io.dvlt.themoviedbtest.global.extension.drawRating

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Float,
    spaceBetween: Dp = 0.dp,
    totalStarCount: Int = 10,
) {

    val image = ImageBitmap.imageResource(id = R.drawable.ic_star)
    val imageFull = ImageBitmap.imageResource(id = R.drawable.ic_star_filled)

    val height = LocalDensity.current.run { image.height.toDp() }
    val width = LocalDensity.current.run { image.width.toDp() }
    val space = LocalDensity.current.run { spaceBetween.toPx() }
    val totalWidth = width * totalStarCount + spaceBetween * (totalStarCount - 1)


    Box(
        modifier
            .width(totalWidth)
            .height(height)
            .drawBehind {
                drawRating(rating, image, imageFull, space, totalStarCount)
            })
}