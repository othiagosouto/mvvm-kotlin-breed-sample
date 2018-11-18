package br.com.tsouto.mvvmkotlinbreedsample

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import br.com.tsouto.mvvmkotlinbreedsample.breeds.AdapterItemsContract

class BindingAdapters {

    companion object {
        @BindingAdapter("items")
        @JvmStatic
        fun <T>setItems(recyclerView: RecyclerView, items: MutableLiveData<List<T>>) {

             recyclerView.adapter.let {
                if (it is AdapterItemsContract) {
                    val ref = it
                    items.value?.let{
                        ref.replaceItems(it)
                    }
                }
            }
        }
    }

}