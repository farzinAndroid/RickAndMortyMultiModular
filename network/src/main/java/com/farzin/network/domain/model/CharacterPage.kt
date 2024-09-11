package com.farzin.network.domain.model

data class LocalCharacterPage(
    val info: Info,
    val characters: List<LocalCharacter>
) {
    data class Info(
        val count: Int,
        val pages: Int,
        val next: String?,
        val prev: String?
    )
}