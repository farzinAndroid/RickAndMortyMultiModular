package com.farzin.network.domain.mappers

import com.farzin.network.domain.model.CharacterStatus
import com.farzin.network.domain.model.GenderStatus
import com.farzin.network.domain.model.LocalCharacter
import com.farzin.network.domain.model.Location
import com.farzin.network.domain.model.Origin
import com.farzin.network.remote.dto.RemoteCharacter


fun RemoteCharacter.remoteCharacterToLocalCharacter() =
    LocalCharacter(
        id = this.id,
        name = this.name,
        url = this.url,
        gender = GenderStatus.fromString(this.gender),
        type = this.type,
        image = this.image,
        origin = Origin(this.origin.name, this.origin.url),
        status = CharacterStatus.fromString(this.status),
        location = Location(this.location.name,this.location.url),
        created = this.created,
        episode = this.episode,
        species = this.species

    )
