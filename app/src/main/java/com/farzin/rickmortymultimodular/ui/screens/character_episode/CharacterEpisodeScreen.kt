package com.farzin.rickmortymultimodular.ui.screens.character_episode

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farzin.network.data.client.KtorClient
import com.farzin.network.domain.model.LocalCharacter
import com.farzin.network.domain.model.LocalEpisode
import com.farzin.rickmortymultimodular.ui.screens.character_detail.components.CharacterNamePlateComponent
import com.farzin.rickmortymultimodular.ui.screens.character_episode.components.CharacterImage
import com.farzin.rickmortymultimodular.ui.screens.character_episode.components.CharacterNameComponent
import com.farzin.rickmortymultimodular.ui.screens.character_episode.components.EpisodeRowComponent
import com.farzin.rickmortymultimodular.ui.screens.character_episode.components.SeasonHeader
import com.farzin.rickmortymultimodular.ui.screens.components.Loading
import com.farzin.rickmortymultimodular.ui.theme.RickPrimary
import kotlinx.coroutines.launch

@Composable
fun CharacterEpisodeScreen(
    characterId:Int,
    ktorClient: KtorClient
) {

    var character by remember {
        mutableStateOf<LocalCharacter?>(null)
    }
    var episodesState by remember {
        mutableStateOf<List<LocalEpisode>>(emptyList())
    }

    LaunchedEffect(true) {
        ktorClient
            .getCharacter(characterId)
            .onSuccess {
                character = it
                Log.e("TAG",character?.episode.toString())
                launch {
                    ktorClient.getEpisodes(character?.episode ?: emptyList())
                        .onSuccess {episodes->
                            episodesState = episodes
                        }
                        .onFailure {

                        }
                }
            }
            .onFailure {
                // todo exception
            }
    }

    character?.let {
        MainScreen(
            character=it,
            episode = episodesState

        )
    }?: Loading()

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(character:LocalCharacter,episode: List<LocalEpisode>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(RickPrimary)
            .padding(PaddingValues(16.dp))
            .padding(bottom = 64.dp)
    ) {

        item { CharacterNameComponent(name = character.name) }
        item { Spacer(Modifier.height(16.dp)) }
        item { CharacterImage(imageUrl=character.image) }
        item { Spacer(Modifier.height(32.dp)) }

        episode.groupBy { it.seasonNumber }.forEach { mapEntry ->
            val seasonNumber = mapEntry.key
            val episodesList = mapEntry.value
            stickyHeader {
                SeasonHeader(seasonNumber = seasonNumber)
            }

            items(episodesList){
                Spacer(Modifier.height(16.dp))
                EpisodeRowComponent(episode = it)
            }
        }












    }
}