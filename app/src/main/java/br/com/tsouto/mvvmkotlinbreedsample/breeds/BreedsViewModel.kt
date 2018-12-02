package br.com.tsouto.mvvmkotlinbreedsample.breeds

import android.app.Application
import android.arch.lifecycle.*
import br.com.tsouto.mvvmkotlinbreedsample.R
import br.com.tsouto.mvvmkotlinbreedsample.data.Breed
import br.com.tsouto.mvvmkotlinbreedsample.data.BreedDataSource

class BreedsViewModel(val repository: BreedDataSource, application: Application) :
        AndroidViewModel(application), LifecycleObserver {


    val breeds = MutableLiveData<List<Breed>>().apply { value = emptyList() }
    val loadingVisibility = MutableLiveData<Boolean>().apply { value = false }
    val message = MutableLiveData<String>().apply { value = "" }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun load() {
        loadingVisibility.value = true
        message.value = ""
        repository.listAll({ items ->
            breeds.value = (items)
            if (items.isEmpty()) {
                message.value = getApplication<Application>().getString(R.string.breed_empty)
            }
            loadingVisibility.value = false
        }, {
            message.value = getApplication<Application>().getString(R.string.breed_failed)
            loadingVisibility.value = false
        })
    }

}