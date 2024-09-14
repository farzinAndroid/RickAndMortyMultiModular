package com.farzin.rickmortymultimodular.ui.screens.character_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.farzin.rickmortymultimodular.ui.screens.character_detail.components.BorderedButton
import com.farzin.rickmortymultimodular.ui.screens.character_detail.components.CharacterDataPointComponent
import com.farzin.rickmortymultimodular.ui.screens.character_detail.components.CharacterNamePlateComponent
import com.farzin.rickmortymultimodular.ui.screens.common_components.Loading
import com.farzin.rickmortymultimodular.ui.screens.common_components.SimpleToolbar
import com.farzin.rickmortymultimodular.ui.theme.RickPrimary
import com.farzin.rickmortymultimodular.viewmodel.CharacterDetailsViewmodel

@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailsViewmodel = hiltViewModel(),
    characterId: Int,
    navController: NavController,
    onEpisodeButtonClicked: (Int) -> Unit,
) {

    LaunchedEffect(true) {
        viewModel.getCharacterDetails(characterId)
    }

    val state by viewModel.characterDetailsViewState.collectAsState()

    Column(
        modifier = Modifier
            .background(RickPrimary)
    ) {
        SimpleToolbar("Character Detail", backAction = { navController.navigateUp() })
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(RickPrimary),
            contentPadding = PaddingValues(16.dp)
        ) {

            when (val viewState = state) {
                CharacterDetailsViewState.Loading -> item { Loading() }
                is CharacterDetailsViewState.Error -> {
                    // todo error
                }

                is CharacterDetailsViewState.Success -> {
                    item {
                        CharacterNamePlateComponent(
                            name = viewState.character.name,
                            status = viewState.character.status
                        )
                    }

                    item { Spacer(Modifier.height(8.dp)) }

                    item {
                        SubcomposeAsyncImage(
                            model = viewState.character.image,
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(12.dp)),
                            loading = {
                                Loading()
                            }
                        )
                    }


                    items(viewState.dataPoints) {
                        Spacer(Modifier.height(32.dp))
                        CharacterDataPointComponent(it)
                    }


                    item { Spacer(Modifier.height(32.dp)) }


                    item {
                        BorderedButton(
                            onClick = { onEpisodeButtonClicked(characterId) }
                        )
                    }

                    item { Spacer(Modifier.height(64.dp)) }
                }
            }


        }
    }

}