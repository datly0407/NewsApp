package com.datly.newsapp.di

import com.datly.newsapp.data.NewsRepositoryFactory
import com.datly.newsapp.network.Network
import com.datly.newsapp.network.NetworkImpl
import com.datly.newsapp.util.ScreenUtil
import com.datly.newsapp.util.ScreenUtilImpl
import com.datly.newsapp.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    factory { NewsRepositoryFactory.buildNewsRepository() }
    single<ScreenUtil> { ScreenUtilImpl() }
    single<Network> { NetworkImpl() }
    viewModel { NewsViewModel(get()) }
}