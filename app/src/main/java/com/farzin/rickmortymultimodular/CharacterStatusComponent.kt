package com.farzin.rickmortymultimodular

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farzin.network.domain.model.CharacterStatus
import com.farzin.rickmortymultimodular.ui.theme.RickMortyMultiModularTheme

@Composable
fun CharacterStatusComponent(characterStatus: CharacterStatus) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Red,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = "Status: ${characterStatus.status}",
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun CharacterStatusComponentPreviewAlive() {
    RickMortyMultiModularTheme {
        CharacterStatusComponent(characterStatus = CharacterStatus.Alive)
    }
}

@Preview
@Composable
fun CharacterStatusComponentPreviewDead() {
    RickMortyMultiModularTheme {
        CharacterStatusComponent(characterStatus = CharacterStatus.Dead)
    }
}

@Preview
@Composable
fun CharacterStatusComponentPreviewUnknown() {
    RickMortyMultiModularTheme {
        CharacterStatusComponent(characterStatus = CharacterStatus.Unknown)
    }
}