package com.farzin.rickmortymultimodular.navigation

sealed class Screens(val route:String) {

    data object CharacterDetail : Screens("character_detail")
    data object CharacterEpisodes : Screens("character_episodes")
}