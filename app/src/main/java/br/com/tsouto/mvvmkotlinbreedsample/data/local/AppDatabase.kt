package br.com.tsouto.mvvmkotlinbreedsample.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.tsouto.mvvmkotlinbreedsample.data.Breed

@Database(entities = arrayOf(Breed::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun breedDao(): BreedDao
}