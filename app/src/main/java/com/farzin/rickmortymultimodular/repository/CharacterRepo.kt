package com.farzin.rickmortymultimodular.repository

import com.farzin.network.data.client.ApiOperation
import com.farzin.network.data.client.KtorClient
import com.farzin.network.domain.model.LocalCharacter
import javax.inject.Inject

class CharacterRepo @Inject constructor(
    private val ktorClient: KtorClient
) {

    suspend fun getCharacterDetails(characterId: Int): ApiOperation<LocalCharacter> =
        ktorClient.getCharacter(characterId)



}