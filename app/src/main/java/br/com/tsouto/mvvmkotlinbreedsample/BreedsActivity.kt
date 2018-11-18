package br.com.tsouto.mvvmkotlinbreedsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.breeds_activity.*

class BreedsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.breeds_activity)
        addFragmentTo(R.id.content_frame, createFragment())
        setSupportActionBar(toolbar)
    }


    fun createFragment(): BreedsFragment {
        return BreedsFragment.newInstance()
    }

}
