package com.hackathon2019.padeitha.ui.home.pack

import com.hackathon2019.padeitha.domain.responses.packs.PacksResponse

sealed class PacksViewState {
    object Loading : PacksViewState()
    data class DataReady(val response: PacksResponse) : PacksViewState()
    data class Error(val error: Throwable) : PacksViewState()
    data class InternetConnection(val msg: String) : PacksViewState()
}