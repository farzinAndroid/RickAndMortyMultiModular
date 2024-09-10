package com.farzin.rickmortymultimodular.ui.screens.character_episode.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.farzin.rickmortymultimodular.ui.screens.components.Loading



@Composable
fun CharacterImage(imageUrl: String) {
    val defaultModifier = Modifier
        .fillMaxWidth()
        .aspectRatio(1f)
        .clip(RoundedCornerShape(12.dp))


    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = "Character image",
        modifier = defaultModifier,
        loading = { Loading() }
    )
}