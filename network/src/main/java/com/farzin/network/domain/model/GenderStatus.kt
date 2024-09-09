package com.farzin.network.domain.model

sealed class GenderStatus(val gender: String) {

    data object Male : GenderStatus("Male")
    data object Female : GenderStatus("Female")
    data object Genderless : GenderStatus("Genderless")
    data object Unknown : GenderStatus("Unknown")

    companion object{
        fun fromString(gender: String):GenderStatus{
            return when(gender.lowercase() ){
                "male"->Male
                "female"->Female
                "genderless"->Genderless
                "unknown"->Unknown
                else -> Unknown
            }
        }
    }

}