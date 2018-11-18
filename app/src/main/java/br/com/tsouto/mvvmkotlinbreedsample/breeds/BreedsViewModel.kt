package br.com.tsouto.mvvmkotlinbreedsample.breeds

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import br.com.tsouto.mvvmkotlinbreedsample.R
import br.com.tsouto.mvvmkotlinbreedsample.data.Breed
import br.com.tsouto.mvvmkotlinbreedsample.data.BreedDataSource

class BreedsViewModel(val repository: BreedDataSource, application: Application) : AndroidViewModel(application) {


    val breeds = ObservableArrayList<Breed>()
    val loadingVisibility = ObservableBoolean(false)
    val message = ObservableField<String>()

    fun load() {
        loadingVisibility.set(true)
        message.set("")
        repository.listAll({ items ->
            breeds.clear()
            breeds.addAll(items)
            if (items.isEmpty()) {
                message.set(getApplication<Application>().getString(R.string.breed_empty))
            }
            loadingVisibility.set(false)
        }, {
            message.set(getApplication<Application>().getString(R.string.breed_failed))
            loadingVisibility.set(false)
        })
    }
}