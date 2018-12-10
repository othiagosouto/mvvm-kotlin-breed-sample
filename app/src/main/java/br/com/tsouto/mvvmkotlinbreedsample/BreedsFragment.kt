package br.com.tsouto.mvvmkotlinbreedsample

import android.app.Application
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
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
import br.com.tsouto.mvvmkotlinbreedsample.data.local.AppDatabase
import br.com.tsouto.mvvmkotlinbreedsample.data.local.BreedDao
import br.com.tsouto.mvvmkotlinbreedsample.data.local.DogCeoLocalDataSource
import br.com.tsouto.mvvmkotlinbreedsample.data.remote.DogCeoApi
import br.com.tsouto.mvvmkotlinbreedsample.data.remote.DogCeoDataSource
import br.com.tsouto.mvvmkotlinbreedsample.databinding.BreedsFragmentBinding
import br.com.tsouto.mvvmkotlinbreedsample.util.AppExecutors
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BreedsFragment : Fragment() {

    lateinit var viewModel: BreedsViewModel

    companion object {
        fun newInstance() = BreedsFragment()
    }

    override fun onCreateView(@NonNull inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: BreedsFragmentBinding = BreedsFragmentBinding.inflate(inflater, container, false)

        this.activity?.application?.let {
            viewModel = createViewModel(it)
            binding.viewModel = viewModel
            binding.recyclerView.adapter = BreedsAdapter(emptyList())
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)

            binding.setLifecycleOwner(this)
            this.lifecycle.addObserver(viewModel)
        }

        return binding.root
    }

    fun createViewModel(application: Application): BreedsViewModel {
        val retrofit = Retrofit.Builder().baseUrl("http://dog.ceo/api/").addConverterFactory(GsonConverterFactory.create()).build()
        val dogCeoDataSource = DogCeoDataSource(retrofit.create(DogCeoApi::class.java))
        val localDataSource = DogCeoLocalDataSource(breedDao(application), AppExecutors())
        val repository = BreedRepository(dogCeoDataSource, localDataSource)

        val factory = BreedsViewModelFactory(repository, application)

        return ViewModelProviders.of(this, factory).get(BreedsViewModel::class.java)
    }


    fun breedDao(applicationContext: Application): BreedDao {
        return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "breeds-app"
        ).build().breedDao()
    }


}
