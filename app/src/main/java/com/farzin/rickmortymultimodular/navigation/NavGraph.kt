package com.farzin.rickmortymultimodular.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.farzin.network.data.client.KtorClient
import com.farzin.rickmortymultimodular.ui.screens.character_detail.CharacterDetailScreen
import com.farzin.rickmortymultimodular.ui.screens.character_episode.CharacterEpisodeScreen
import com.farzin.rickmortymultimodular.ui.screens.character_list.CharacterListScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    ktorClient: KtorClient
) {


    NavHost(
        navController = navController,
        startDestination = Screens.CharacterList.route
    ){
        composable(
            Screens.CharacterDetail.route+ "/{characterId}",
            arguments = listOf(
                navArgument("characterId"){
                    type = NavType.IntType
                    nullable = false
                    defaultValue = -1
                }
            )
        ){
            it.arguments!!.getInt("characterId")?.let { characterId->
                CharacterDetailScreen(
                    characterId = characterId,
                    onEpisodeButtonClicked = {id->
                        navController.navigate(Screens.CharacterEpisodes.route + "/$id")
                    },
                    navController = navController
                )
            }
        }

        composable(
            Screens.CharacterEpisodes.route + "/{characterId}",
            arguments = listOf(
                navArgument("characterId"){
                    type = NavType.IntType
                    nullable = false
                    defaultValue = -1
                }
            )
        ){
            it.arguments!!.getInt("characterId")?.let { characterId->
                CharacterEpisodeScreen(characterId,ktorClient,navController)
            }
        }


        composable(Screens.CharacterList.route){
            CharacterListScreen(
                navController = navController
            )
        }
    }


}