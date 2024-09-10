package com.farzin.network.data.mappers

import com.farzin.network.domain.model.LocalEpisode
import com.farzin.network.data.dto.RemoteEpisode

fun RemoteEpisode.remoteEpisodeToLocalEpisode(): LocalEpisode {
    return LocalEpisode(
        id = id,
        name = name,
        seasonNumber = episode.filter { it.isDigit() }.take(2).toInt(),
        episodeNumber = episode.filter { it.isDigit() }.takeLast(2).toInt(),
        airDate = air_date,
        characterIdsInEpisode = characters.map {
            it.substring(startIndex = it.lastIndexOf("/") + 1).toInt()
        }
    )
}