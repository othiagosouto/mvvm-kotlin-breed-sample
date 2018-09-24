package br.com.tsouto.mvvmkotlinbreedsample.di

import br.com.tsouto.mvvmkotlinbreedsample.breeds.BreedsViewModel
import br.com.tsouto.mvvmkotlinbreedsample.data.BreedDataSource
import br.com.tsouto.mvvmkotlinbreedsample.data.BreedRepository
import br.com.tsouto.mvvmkotlinbreedsample.data.remote.DogCeoApi
import br.com.tsouto.mvvmkotlinbreedsample.data.remote.DogCeoDataSource
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BreedsModule = module {
    single { Retrofit.Builder().baseUrl("http://dog.ceo/api/").addConverterFactory(GsonConverterFactory.create()).build() }
    single { (get() as Retrofit).create(DogCeoApi::class.java) }

    single  ("api"){DogCeoDataSource(get())  as BreedDataSource }
    single("repository") { BreedRepository(get("api")) as BreedDataSource}
    viewModel { BreedsViewModel(get("repository"), get())}
}


/**
 * Module list
 */
val breedsModule = listOf(BreedsModule)
