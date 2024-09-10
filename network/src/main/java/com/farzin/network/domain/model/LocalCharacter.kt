package com.farzin.network.domain.model


data class LocalCharacter(
    val created: String,
    val episode: List<Int>,
    val gender: GenderStatus,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: CharacterStatus,
    val type: String,
    val url: String,
)


data class Location(
    val name: String,
    val url: String,
)



data class Origin(
    val name: String,
    val url: String,
)