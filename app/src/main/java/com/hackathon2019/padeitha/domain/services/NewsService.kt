package com.hackathon2019.padeitha.domain.services

import com.hackathon2019.padeitha.domain.responses.news.NewsResponses
import com.hackathon2019.padeitha.domain.responses.packs.PacksResponse
import retrofit2.http.GET
import io.reactivex.Observable

interface NewsService {

    @GET("news")
    fun loadNews() : Observable<NewsResponses>

}