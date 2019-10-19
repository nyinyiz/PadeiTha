package com.hackathon2019.padeitha.domain.responses.packs

data class PacksResponse(
    val packs: List<Pack>,
    val success: Boolean
)