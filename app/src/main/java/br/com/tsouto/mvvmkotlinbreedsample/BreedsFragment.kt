package br.com.tsouto.mvvmkotlinbreedsample

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.tsouto.mvvmkotlinbreedsample.breeds.BreedsAdapter
import br.com.tsouto.mvvmkotlinbreedsample.breeds.BreedsViewModel
import br.com.tsouto.mvvmkotlinbreedsample.databinding.BreedsFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel


class BreedsFragment : Fragment() {

    val viewModel: BreedsViewModel by viewModel()


    companion object {
        fun newInstance(): BreedsFragment {
            return BreedsFragment()
        }
    }

    override fun onCreateView(@NonNull inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : BreedsFragmentBinding = BreedsFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.recyclerView.adapter = BreedsAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.setLifecycleOwner(this)

        lifecycle.addObserver(viewModel)
        return binding.root
    }

}


