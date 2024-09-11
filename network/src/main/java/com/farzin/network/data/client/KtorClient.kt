package com.farzin.network.data.client

import com.farzin.network.data.dto.RemoteCharacter
import com.farzin.network.data.dto.RemoteCharacterPage
import com.farzin.network.data.dto.RemoteEpisode
import com.farzin.network.data.dto.remoteCharacterPageToLocalCharacterPage
import com.farzin.network.data.mappers.remoteCharacterToLocalCharacter
import com.farzin.network.data.mappers.remoteEpisodeToLocalEpisode
import com.farzin.network.domain.model.LocalCharacter
import com.farzin.network.domain.model.LocalCharacterPage
import com.farzin.network.domain.model.LocalEpisode
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

    private val client = HttpClient(OkHttp) {
        defaultRequest {
            url("https://rickandmortyapi.com/api/")
        }

        install(Logging) {
            logger = Logger.SIMPLE
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }


    private val characterCache = mutableMapOf<Int, LocalCharacter>()
    suspend fun getCharacter(id: Int): ApiOperation<LocalCharacter> {
        characterCache[id]?.let { return ApiOperation.Success(it) }
        return safeApiCall {
            client.get("character/$id")
                .body<RemoteCharacter>()
                .remoteCharacterToLocalCharacter()
                .also {
                    characterCache[id] = it
                }
        }
    }


    private suspend fun getEpisode(episodeId: Int): ApiOperation<LocalEpisode> {
        return safeApiCall {
            client.get("episode/$episodeId")
                .body<RemoteEpisode>()
                .remoteEpisodeToLocalEpisode()
        }
    }

    suspend fun getEpisodes(episodeIds: List<Int>): ApiOperation<List<LocalEpisode>> {
        if (episodeIds.size == 1) {
            return getEpisode(episodeIds[0])
                .mapSuccess {
                    listOf(it)
                }
        }else{
            val idsCommaSeperated = episodeIds.joinToString(",")
            return safeApiCall {
                client.get("episode/$idsCommaSeperated")
                    .body<List<RemoteEpisode>>()
                    .map { it.remoteEpisodeToLocalEpisode() }
            }
        }
    }


    suspend fun getCharacterList(pageNumber:Int) : ApiOperation<LocalCharacterPage>{
        return safeApiCall {
            client.get("character/?page=$pageNumber")
                .body<RemoteCharacterPage>()
                .remoteCharacterPageToLocalCharacterPage()
        }
    }


    private inline fun <T> safeApiCall(apiCall: () -> T): ApiOperation<T> {
        return try {
            ApiOperation.Success(data = apiCall())
        } catch (e: Exception) {
            ApiOperation.Failure(exception = e)
        }
    }

}