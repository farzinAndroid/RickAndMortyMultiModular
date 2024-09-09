package com.farzin.network.domain.model


sealed class CharacterStatus(val status: String) {

    data object Alive : CharacterStatus("Alive")
    data object Dead : CharacterStatus("Dead")
    data object Unknown : CharacterStatus("Unknown")

    companion object{
        fun fromString(status: String):CharacterStatus{
            return when(status.lowercase() ){
                "alive"-> Alive
                "dead"-> Dead
                "unknown"-> Unknown
                else -> Unknown
            }
        }
    }

}