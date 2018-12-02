package br.com.tsouto.mvvmkotlinbreedsample

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.tsouto.mvvmkotlinbreedsample.breeds.BreedsViewModel
import br.com.tsouto.mvvmkotlinbreedsample.data.BreedDataSource

class BreedsViewModelFactory constructor(private val repository: BreedDataSource, private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(BreedsViewModel::class.java) ->
                        BreedsViewModel(repository, application)
                    else ->
                        throw IllegalArgumentException("Classe desconhecida.")
                }
            } as T
}