package com.netflixclone.screens.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.netflixclone.R
import com.netflixclone.ui.focusOutline
import com.netflixclone.ui.isTelevision

@Composable
fun Poster(url: String?, isLarge: Boolean = false, onClick: () -> Unit) {
    // Slightly bigger posters on a TV so they read well from the sofa.
    val tv = isTelevision()
    val width = when {
        isLarge && tv -> 200.dp
        isLarge -> 175.dp
        tv -> 130.dp
        else -> 110.dp
    }
    Box(Modifier.padding(start = 8.dp, top = 2.dp)) {
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = Modifier
                .width(width)
                .aspectRatio(0.66f)
                .focusOutline(RoundedCornerShape(4.dp))
                .clip(RoundedCornerShape(4.dp))
                .background(colorResource(R.color.dark_gray))
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(),
                    onClick = onClick
                ),
            contentScale = ContentScale.Crop,
        )

        if (isLarge) {
            Column(Modifier
                .width(width)
                .aspectRatio(0.66f)) {
                Box(Modifier
                    .fillMaxWidth()
                    .weight(0.8f)
                )
                Box(Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 1f)
                            )
                        )
                    )
                )
            }
        }
    }
}