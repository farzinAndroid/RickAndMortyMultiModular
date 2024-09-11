package com.farzin.network.data.dto

import com.farzin.network.data.mappers.remoteCharacterToLocalCharacter
import com.farzin.network.domain.model.LocalCharacterPage
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCharacterPage(
    val info: Info,
    val results: List<RemoteCharacter>
) {
    @Serializable
    data class Info(
        val count: Int,
        val pages: Int,
        val next: String?,
        val prev: String?
    )
}

fun RemoteCharacterPage.remoteCharacterPageToLocalCharacterPage(): LocalCharacterPage {
    return LocalCharacterPage(
        info = LocalCharacterPage.Info(
            count = info.count,
            pages = info.pages,
            next = info.next,
            prev = info.next
        ),
        characters = results.map { it.remoteCharacterToLocalCharacter() }
    )
}