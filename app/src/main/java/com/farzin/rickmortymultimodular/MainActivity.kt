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
import com.farzin.network.client.KtorClient
import com.farzin.network.domain.model.LocalCharacter
import com.farzin.network.remote.dto.RemoteCharacter
import com.farzin.rickmortymultimodular.ui.screens.character_detail.CharacterDetailScreen
import com.farzin.rickmortymultimodular.ui.theme.RickMortyMultiModularTheme
import com.farzin.rickmortymultimodular.ui.theme.RickPrimary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {

    private val ktorClient = KtorClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            RickMortyMultiModularTheme {

                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                ) {
                    CharacterDetailScreen(
                        ktorClient = ktorClient,
                        id = 85
                    )
                }
            }
        }
    }
}