package br.com.tsouto.mvvmkotlinbreedsample.data

import br.com.tsouto.mvvmkotlinbreedsample.data.local.DogCeoLocalDataSource

class BreedRepository(private val remoteDataSource: BreedDataSource, private val dogCeoLocalDataSource: DogCeoLocalDataSource) : BreedDataSource {
    override fun save(breed: Breed) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listAll(success: (List<Breed>) -> Unit, failure: () -> Unit) {

        remoteDataSource.listAll({
            it.forEach (dogCeoLocalDataSource::save)
            success(it)

        }, {
            dogCeoLocalDataSource.listAll(success, failure)
        })
    }


}