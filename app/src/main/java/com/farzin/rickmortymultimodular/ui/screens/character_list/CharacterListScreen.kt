package com.farzin.rickmortymultimodular.ui.screens.character_list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.rickmortymultimodular.navigation.Screens
import com.farzin.rickmortymultimodular.ui.screens.character_list.components.CharacterGridItem
import com.farzin.rickmortymultimodular.ui.screens.common_components.Loading
import com.farzin.rickmortymultimodular.ui.screens.common_components.SimpleToolbar
import com.farzin.rickmortymultimodular.ui.theme.RickPrimary
import com.farzin.rickmortymultimodular.viewmodel.CharacterListViewmodel

@Composable
fun CharacterListScreen(
    viewmodel: CharacterListViewmodel = hiltViewModel(),
    navController: NavController,
) {


    LaunchedEffect(viewmodel) {
        viewmodel.getInitialPage()
    }

    val viewState by viewmodel.viewState.collectAsState()


    val lazyGridState = rememberLazyGridState()
    val fetchNextPage: Boolean by remember {
        derivedStateOf {
            val currentCharacterCount =
                (viewState as? CharacterListScreenViewState.GridDisplay)?.characters?.size
                    ?: return@derivedStateOf false
            val lastDisPlayedIndex = lazyGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: return@derivedStateOf false
            return@derivedStateOf lastDisPlayedIndex >= currentCharacterCount - 15
        }
    }
    LaunchedEffect(fetchNextPage) {
        Log.e("TAG",fetchNextPage.toString())
        if (fetchNextPage){
            viewmodel.getNextPage()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RickPrimary)
    ) {
        when (val state = viewState) {
            CharacterListScreenViewState.Loading -> {
                Loading()
            }

            is CharacterListScreenViewState.GridDisplay -> {
                Column {
                    SimpleToolbar(title = "Characters")
                    LazyVerticalGrid(
                        state = lazyGridState,
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .background(RickPrimary),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.characters, key = { it.id }) {
                            CharacterGridItem(
                                character = it,
                                onClick = { characterId ->
                                    navController.navigate(Screens.CharacterDetail.route + "/$characterId")
                                }
                            )
                        }
                    }
                }
            }
        }

    }


}