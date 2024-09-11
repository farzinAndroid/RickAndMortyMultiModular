package com.farzin.rickmortymultimodular.ui.screens.character_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farzin.network.domain.model.CharacterStatus

@Composable
fun CharacterStatusCircle(
    modifier: Modifier = Modifier,
    characterStatus: CharacterStatus
) {

    val color = when(characterStatus){
        CharacterStatus.Alive -> Color.Green
        CharacterStatus.Dead -> Color.Red
        CharacterStatus.Unknown -> Color.Yellow
    }

    Box(
        modifier = modifier
            .background(
                brush = Brush.radialGradient(listOf(Color.Black,Color.Transparent)),
                shape = CircleShape
            )
            .size(20.dp),
        contentAlignment = Alignment.Center
    ){
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(color, CircleShape)
        )
    }

}