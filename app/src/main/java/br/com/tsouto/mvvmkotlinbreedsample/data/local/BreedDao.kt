package br.com.tsouto.mvvmkotlinbreedsample.data.local

import android.arch.persistence.room.*
import br.com.tsouto.mvvmkotlinbreedsample.data.Breed

@Dao
interface BreedDao {
    @Query("SELECT * FROM breed")
    fun getAll(): List<Breed>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg breeds: Breed)

    @Delete
    fun delete(breed: Breed)
}