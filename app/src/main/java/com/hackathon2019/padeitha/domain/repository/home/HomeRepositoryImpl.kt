package com.hackathon2019.padeitha.domain.repository.home

import com.hackathon2019.padeitha.domain.responses.faq.FAQResponse
import com.hackathon2019.padeitha.domain.responses.news.NewsResponses
import com.hackathon2019.padeitha.domain.responses.packs.PacksResponse
import com.hackathon2019.padeitha.domain.services.FAQService
import com.hackathon2019.padeitha.domain.services.NewsService
import com.hackathon2019.padeitha.domain.services.PacksService
import io.reactivex.Observable

class HomeRepositoryImpl(
    private val servicePacks : PacksService,
    private val serviceNews : NewsService,
    private val serviceFAQ : FAQService
) : HomeRepository {

    override fun loadFAQ(): Observable<FAQResponse> {
        return serviceFAQ.loadFAQ()
    }

    override fun loadNews(): Observable<NewsResponses> {
        return serviceNews.loadNews()
    }

    override fun loadPacks(): Observable<PacksResponse> {
        return servicePacks.loadPacks()
    }

}