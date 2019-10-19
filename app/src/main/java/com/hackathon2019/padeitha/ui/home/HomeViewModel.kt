package com.hackathon2019.padeitha.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hackathon2019.padeitha.domain.repository.home.HomeRepository
import com.hackathon2019.padeitha.ui.home.news.NewsViewState
import com.hackathon2019.padeitha.ui.home.pack.PacksViewState
import com.hackathon2019.padeitha.ui.questionAnswer.FAQViewState
import com.nyinyi.mymovie.data.api.inject.NetworkException
import com.nyinyi.mymovie.utilits.addTo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel (
    private val repository: HomeRepository,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    val viewStateNews: MutableLiveData<NewsViewState> = MutableLiveData()
    val viewStatePacks: MutableLiveData<PacksViewState> = MutableLiveData()
    val viewStateFAQ: MutableLiveData<FAQViewState> = MutableLiveData()

    fun loadFAQ() {

        repository.loadFAQ().subscribeOn(Schedulers.io())
            .map<FAQViewState> {
                FAQViewState.DataReady(
                    it
                )
            }
            .startWith(FAQViewState.Loading)
            .onErrorReturn { FAQViewState.Error(it) }
            .observeOn(Schedulers.io())
            .subscribe({
                viewStateFAQ.postValue(it)
            }, { error ->
                when (error) {
                    is NetworkException ->
                        viewStateFAQ.postValue(
                            FAQViewState.InternetConnection(
                                "No internet connection."
                            )
                        )
                    else ->
                        viewStateFAQ.postValue(
                            FAQViewState.Error(
                                error
                            )
                        )
                }
            }).addTo(compositeDisposable)

    }
    fun loadPacks() {
        repository.loadPacks().subscribeOn(Schedulers.io())
            .map<PacksViewState> {
                PacksViewState.DataReady(
                    it
                )
            }
            .startWith(PacksViewState.Loading)
            .onErrorReturn { PacksViewState.Error(it) }
            .observeOn(Schedulers.io())
            .subscribe({
                viewStatePacks.postValue(it)
            }, { error ->
                when (error) {
                    is NetworkException ->
                        viewStatePacks.postValue(
                            PacksViewState.InternetConnection(
                                "No internet connection."
                            )
                        )
                    else ->
                        viewStatePacks.postValue(
                            PacksViewState.Error(
                                error
                            )
                        )
                }
            }).addTo(compositeDisposable)
    }

    fun loadNews() {
        repository.loadNews().subscribeOn(Schedulers.io())
            .map<NewsViewState> {
                NewsViewState.DataReady(
                    it
                )
            }
            .startWith(NewsViewState.Loading)
            .onErrorReturn { NewsViewState.Error(it) }
            .observeOn(Schedulers.io())
            .subscribe({
                viewStateNews.postValue(it)
            }, { error ->
                when (error) {
                    is NetworkException ->
                        viewStateNews.postValue(
                            NewsViewState.InternetConnection(
                                "No internet connection."
                            )
                        )
                    else ->
                        viewStateNews.postValue(
                            NewsViewState.Error(
                                error
                            )
                        )
                }
            }).addTo(compositeDisposable)

    }

}