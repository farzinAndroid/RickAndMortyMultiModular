package com.farzin.rickmortymultimodular

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.farzin.network.data.client.KtorClient
import com.farzin.network.domain.model.LocalCharacter
import com.farzin.network.data.dto.RemoteCharacter
import com.farzin.rickmortymultimodular.navigation.NavGraph
import com.farzin.rickmortymultimodular.ui.screens.character_detail.CharacterDetailScreen
import com.farzin.rickmortymultimodular.ui.theme.RickMortyMultiModularTheme
import com.farzin.rickmortymultimodular.ui.theme.RickPrimary
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var ktorClient: KtorClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            RickMortyMultiModularTheme {

                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                ) {
                    NavGraph(
                        navController = navController,
                        ktorClient = ktorClient
                    )
                }
            }
        }
    }
}