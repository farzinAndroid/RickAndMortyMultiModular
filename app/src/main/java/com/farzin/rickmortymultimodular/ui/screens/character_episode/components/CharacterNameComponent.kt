package com.farzin.rickmortymultimodular.ui.screens.character_episode.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.farzin.rickmortymultimodular.ui.theme.RickAction

@Composable
fun CharacterNameComponent(name: String) {
    Text(
        text = name,
        fontSize = 42.sp,
        lineHeight = 42.sp,
        fontWeight = FontWeight.Bold,
        color = RickAction
    )
}