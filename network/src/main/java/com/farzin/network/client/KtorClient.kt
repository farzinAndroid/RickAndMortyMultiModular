package com.farzin.network.client

import com.farzin.network.domain.mappers.remoteCharacterToLocalCharacter
import com.farzin.network.domain.model.LocalCharacter
import com.farzin.network.remote.dto.RemoteCharacter
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {

    private val  client = HttpClient(OkHttp){
        defaultRequest {
            url("https://rickandmortyapi.com/api/")
        }

        install(Logging){
            logger = Logger.SIMPLE
        }

        install(ContentNegotiation){
            json(
                Json{
                    ignoreUnknownKeys = true
                }
            )
        }
    }


    suspend fun getCharacter(id:Int) : LocalCharacter {
        return client.get("character/$id")
            .body<RemoteCharacter>()
            .remoteCharacterToLocalCharacter()
    }

}