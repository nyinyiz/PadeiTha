package com.hackathon2019.padeitha.ui.home.news

import com.hackathon2019.padeitha.domain.responses.news.NewsResponses


sealed class NewsViewState {
    object Loading : NewsViewState()
    data class DataReady(val response: NewsResponses) : NewsViewState()
    data class Error(val error: Throwable) : NewsViewState()
    data class InternetConnection(val msg: String) : NewsViewState()
}