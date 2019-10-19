package com.hackathon2019.padeitha.domain.repository.home

import com.hackathon2019.padeitha.domain.responses.faq.FAQResponse
import com.hackathon2019.padeitha.domain.responses.news.NewsResponses
import com.hackathon2019.padeitha.domain.responses.packs.PacksResponse
import io.reactivex.Observable

interface HomeRepository {
    fun loadPacks() : Observable<PacksResponse>
    fun loadNews() : Observable<NewsResponses>
    fun loadFAQ() : Observable<FAQResponse>
}