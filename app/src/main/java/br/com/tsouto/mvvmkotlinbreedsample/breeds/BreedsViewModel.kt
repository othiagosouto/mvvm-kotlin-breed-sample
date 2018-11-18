package br.com.tsouto.mvvmkotlinbreedsample.breeds

import android.app.Application
import android.arch.lifecycle.*
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import br.com.tsouto.mvvmkotlinbreedsample.R
import br.com.tsouto.mvvmkotlinbreedsample.data.Breed
import br.com.tsouto.mvvmkotlinbreedsample.data.BreedDataSource

class BreedsViewModel(private val repository: BreedDataSource, private val application: Application) : ViewModel(), LifecycleObserver {

    var breeds = MutableLiveData<List<Breed>>()
    val loadingVisibility = ObservableBoolean(false)
    val message = ObservableField<String>()

    private fun load() {
        loadingVisibility.set(true)
        message.set("")
        repository.listAll({ items ->
            breeds.postValue(items)
            if (items.isEmpty()) {
                message.set(application.getString(R.string.breed_empty))
            }
            loadingVisibility.set(false)
        }, {
            message.set(application.getString(R.string.breed_failed))
            loadingVisibility.set(false)
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        Log.d("TESTE", "ON_RESUME")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.d("TESTE", "PAUSE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.d("TESTE", "ON_START")
        load()

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d("TESTE", "ON_CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.d("TESTE", "ON_STOP")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.d("TESTE", "ON_DESTROY")
    }


}