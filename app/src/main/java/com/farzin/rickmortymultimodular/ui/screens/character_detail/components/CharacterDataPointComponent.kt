package com.farzin.rickmortymultimodular.ui.screens.character_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.farzin.rickmortymultimodular.ui.theme.RickAction
import com.farzin.rickmortymultimodular.ui.theme.RickPrimary
import com.farzin.rickmortymultimodular.ui.theme.RickTextPrimary

@Composable
fun CharacterDataPointComponent(
    dataPoint: DataPoint,
) {

    Column {
        Text(
            text = dataPoint.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = RickAction
        )

        Text(
            text = dataPoint.description,
            fontSize = 24.sp,
            color = RickTextPrimary
        )
    }

}



data class DataPoint(
    val title:String,
    val description:String
)