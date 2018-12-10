package br.com.tsouto.mvvmkotlinbreedsample.data

interface BreedDataSource {

    fun listAll(success: (List<Breed>) -> Unit, failure: () -> Unit)

    fun save(breed: Breed)

}