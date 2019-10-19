package com.hackathon2019.padeitha.domain.responses.news

data class New(
    val Body: String,
    val PostImage: String,
    val PostedDate: String,
    val PostedUser: String,
    val PostedUserImg: String,
    val Title: String
)