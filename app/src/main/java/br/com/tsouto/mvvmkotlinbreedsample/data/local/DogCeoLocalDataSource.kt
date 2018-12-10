package br.com.tsouto.mvvmkotlinbreedsample.data.local

import br.com.tsouto.mvvmkotlinbreedsample.data.Breed
import br.com.tsouto.mvvmkotlinbreedsample.data.BreedDataSource
import br.com.tsouto.mvvmkotlinbreedsample.util.AppExecutors

class DogCeoLocalDataSource(private val dao: BreedDao, private val appExecutors: AppExecutors) : BreedDataSource {

    override fun save(breed: Breed) {
        appExecutors.roomThreadExecutor.execute {
            dao.insertAll(breed)
        }
    }

    override fun listAll(success: (List<Breed>) -> Unit, failure: () -> Unit) {
        appExecutors.roomThreadExecutor.execute {
            val breeds = dao.getAll()
            appExecutors.mainThreadExecutor.execute { success(breeds) }
        }
    }

}