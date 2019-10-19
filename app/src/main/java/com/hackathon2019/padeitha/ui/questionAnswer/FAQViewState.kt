package com.hackathon2019.padeitha.ui.questionAnswer

import com.hackathon2019.padeitha.domain.responses.faq.FAQResponse

sealed class FAQViewState {
    object Loading : FAQViewState()
    data class DataReady(val response: FAQResponse) : FAQViewState()
    data class Error(val error: Throwable) : FAQViewState()
    data class InternetConnection(val msg: String) : FAQViewState()
}