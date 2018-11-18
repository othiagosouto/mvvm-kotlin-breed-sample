package br.com.tsouto.mvvmkotlinbreedsample

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.tsouto.mvvmkotlinbreedsample.breeds.BreedsAdapter
import br.com.tsouto.mvvmkotlinbreedsample.breeds.BreedsViewModel
import br.com.tsouto.mvvmkotlinbreedsample.data.BreedRepository
import br.com.tsouto.mvvmkotlinbreedsample.data.remote.DogCeoApi
import br.com.tsouto.mvvmkotlinbreedsample.data.remote.DogCeoDataSource
import br.com.tsouto.mvvmkotlinbreedsample.databinding.BreedsFragmentBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BreedsFragment : Fragment() {

    lateinit var viewModel: BreedsViewModel


    companion object {
        fun newInstance() = BreedsFragment()
    }

    override fun onStart() {
        super.onStart()
        viewModel.load()
    }

    override fun onCreateView(@NonNull inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: BreedsFragmentBinding = BreedsFragmentBinding.inflate(inflater, container, false)
        this.viewModel = createViewModel()
        binding.viewModel = viewModel
        binding.recyclerView.adapter = BreedsAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        return binding.root
    }

    fun createViewModel(): BreedsViewModel {
        val retrofit = Retrofit.Builder().baseUrl("http://dog.ceo/api/").addConverterFactory(GsonConverterFactory.create()).build()
        val dogCeoDataSource = DogCeoDataSource(retrofit.create(DogCeoApi::class.java))
        val repository = BreedRepository(dogCeoDataSource)

        val factory = ViewModelFactory(repository, activity?.application!!)

        return ViewModelProviders.of(this, factory).get(BreedsViewModel::class.java)
    }


}
