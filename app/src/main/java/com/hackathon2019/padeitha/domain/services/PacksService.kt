package com.hackathon2019.padeitha.domain.services

import com.hackathon2019.padeitha.domain.responses.packs.PacksResponse
import retrofit2.http.GET
import io.reactivex.Observable

interface PacksService {

    @GET("packs")
    fun loadPacks() : Observable<PacksResponse>

}