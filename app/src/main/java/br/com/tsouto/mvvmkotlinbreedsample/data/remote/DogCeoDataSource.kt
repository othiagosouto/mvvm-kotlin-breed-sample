package br.com.tsouto.mvvmkotlinbreedsample.data.remote

import br.com.tsouto.mvvmkotlinbreedsample.data.Breed
import br.com.tsouto.mvvmkotlinbreedsample.data.BreedDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DogCeoDataSource(val dogCeoApi: DogCeoApi) : BreedDataSource {
    override fun save(breed: Breed) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun listAll(success: (List<Breed>) -> Unit, failure: () -> Unit) {
        val call = dogCeoApi.listBreeds()
        call.enqueue(object : Callback<DogCeoResponse> {

            override fun onResponse(call: Call<DogCeoResponse>, response: Response<DogCeoResponse>) {
                if (response.isSuccessful) {
                    val breeds = mutableListOf<Breed>()
                    response.body()?.message?.forEach {
                        breeds.add(Breed(it.hashCode(), it))
                    }
                    success(breeds)
                } else {
                    failure()
                }
            }

            override fun onFailure(call: Call<DogCeoResponse>, t: Throwable?) {
                failure()
            }
        })

    }
}