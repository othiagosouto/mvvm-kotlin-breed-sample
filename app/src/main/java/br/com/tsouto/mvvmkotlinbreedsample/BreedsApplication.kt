package br.com.tsouto.mvvmkotlinbreedsample

import android.app.Application
import br.com.tsouto.mvvmkotlinbreedsample.di.breedsModule
import org.koin.android.ext.android.startKoin

class BreedsApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin(this, breedsModule)

    }
}