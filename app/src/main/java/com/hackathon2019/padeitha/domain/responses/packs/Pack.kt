package com.hackathon2019.padeitha.domain.responses.packs

data class Pack(
    val ContactNo: String,
    val CreatedAt: String,
    val PackAvailability: Boolean,
    val PackDescription: String,
    val PackID: String,
    val PackImgURL: String,
    val PackPartner: String,
    val PackTitle: String
)