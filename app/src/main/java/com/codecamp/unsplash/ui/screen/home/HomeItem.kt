package com.codecamp.unsplash.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.codecamp.unsplash.R
import com.codecamp.unsplash.domain.model.UnsplashImage

@Composable
fun HomeItem(image: UnsplashImage) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = image.urls.regular,
            contentScale = ContentScale.Crop,
            contentDescription = "",
            placeholder = painterResource(id = R.drawable.placeholder),
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .alpha(0.4F)
                .background(Color.Black)
                .align(Alignment.BottomCenter)
        ) {

        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
            Text(text = buildAnnotatedString {
                append("Image by: ")
                append(image.user.name)
                append(" on Unsplash")
            }, maxLines = 1, color = Color.Black)
            Row {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "",
                    tint = Color.Red
                )
                Text(text = image.likes.toString())
            }
        }
    }
}

