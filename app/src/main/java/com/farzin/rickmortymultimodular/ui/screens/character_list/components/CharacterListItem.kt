package com.farzin.rickmortymultimodular.ui.screens.character_list.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farzin.network.domain.model.LocalCharacter
import com.farzin.rickmortymultimodular.ui.screens.character_detail.components.CharacterDataPointComponent
import com.farzin.rickmortymultimodular.ui.screens.character_detail.components.DataPoint
import com.farzin.rickmortymultimodular.ui.screens.common_components.CharacterImage
import com.farzin.rickmortymultimodular.ui.theme.RickAction

@Composable
fun CharacterListItem(
    modifier: Modifier = Modifier,
    character: LocalCharacter,
    onClick: () -> Unit,
    dataPoints: List<DataPoint>,
) {

    Row(
        modifier = modifier
            .height(140.dp)
            .border(
                width = 1.dp,
                brush = Brush.horizontalGradient(listOf(Color.Transparent, RickAction)),
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {

        Box {
            CharacterImage(
                imageUrl = character.image,
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
            )
            CharacterStatusCircle(
                characterStatus = character.status,
                modifier = Modifier.padding(start = 6.dp, top = 6.dp)
            )
        }

        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
            content = {
                items(
                    listOf(DataPoint(title = "Name", description = character.name)) + dataPoints,
                    key = { it.hashCode() }
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(end = 16.dp)
                    ) {
                        CharacterDataPointComponent(dataPoint = sanitizeDataPoints(it))
                    }
                }
            }
        )
    }

}

private fun sanitizeDataPoints(dataPoint: DataPoint):DataPoint{
    val newDescription = if (dataPoint.description.length > 14){
        dataPoint.description.take(12) + "..."
    }else{
        dataPoint.description
    }

    return dataPoint.copy(description = newDescription)
}

