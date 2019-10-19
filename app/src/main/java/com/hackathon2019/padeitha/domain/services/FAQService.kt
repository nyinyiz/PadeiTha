package com.hackathon2019.padeitha.domain.services

import com.hackathon2019.padeitha.domain.responses.faq.FAQResponse
import com.hackathon2019.padeitha.domain.responses.packs.PacksResponse
import retrofit2.http.GET
import io.reactivex.Observable

interface FAQService {

    @GET("faq")
    fun loadFAQ() : Observable<FAQResponse>

}