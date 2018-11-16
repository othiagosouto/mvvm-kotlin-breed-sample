package br.com.tsouto.mvvmkotlinbreedsample.data

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

class LiveDataList<T>{
    private val content: MutableLiveData<List<T>> by lazy {
        MutableLiveData<List<T>>()
    }

    fun update(list: List<T>) : Unit {
        content.value = list
    }

    fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<List<T>>) : Unit {
        content.observe(lifecycleOwner, observer)
    }
    
}