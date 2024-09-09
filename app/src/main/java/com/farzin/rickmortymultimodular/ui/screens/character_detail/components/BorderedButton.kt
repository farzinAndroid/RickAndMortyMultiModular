package com.farzin.rickmortymultimodular.ui.screens.character_detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farzin.rickmortymultimodular.ui.theme.RickAction

@Composable
fun BorderedButton(
    onClick:()->Unit
) {

   Text(
       text = "View all episodes",
       color = RickAction,
       fontSize = 18.sp,
       textAlign = TextAlign.Center,
       modifier = Modifier
           .fillMaxWidth()
           .padding(horizontal = 32.dp)
           .border(
               width = 1.dp,
               color = RickAction,
               shape = RoundedCornerShape(100.dp)
           )
           .clip(RoundedCornerShape(12.dp))
           .clickable { onClick() }
           .padding(vertical = 8.dp)
   )


}