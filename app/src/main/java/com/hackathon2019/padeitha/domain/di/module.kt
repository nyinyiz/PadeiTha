package com.hackathon2019.padeitha.domain.di

import com.codigo.comfort.InternetConnectivityInterceptor
import com.hackathon2019.padeitha.domain.network.ServiceInject
import com.hackathon2019.padeitha.domain.repository.home.HomeRepository
import com.hackathon2019.padeitha.domain.repository.home.HomeRepositoryImpl
import com.hackathon2019.padeitha.ui.home.HomeViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val applicationModule = module(override = true) {

    single {
        CompositeDisposable()
    }

    single {
        ServiceInject().providePacksService(getProperty("BASE_URL"), get())
    }

    single {
        ServiceInject().provideNewsService(getProperty("BASE_URL"), get())
    }

    single {
        ServiceInject().provideFAQService(getProperty("BASE_URL"), get())
    }

    single {
        InternetConnectivityInterceptor(get())
    }

}

val homeModule = module {

    viewModel {
        HomeViewModel(get(),get())
    }

    single<HomeRepository> {
        HomeRepositoryImpl(get(),get(),get())
    }
}

