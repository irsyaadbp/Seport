package com.irsyaad.dicodingsubmission.seport.model.response

data class PlayerResponse (
    val player: ArrayList<ListPlayer>
)

data class ListPlayer(
    val strPlayer: String?,
    val strPosition: String?,
    val strCutout: String?
)