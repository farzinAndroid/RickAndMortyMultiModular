package com.farzin.network.domain.model

data class LocalEpisode(
    val id: Int,
    val name: String,
    val seasonNumber: Int,
    val episodeNumber: Int,
    val airDate: String,
    val characterIdsInEpisode: List<Int>
)