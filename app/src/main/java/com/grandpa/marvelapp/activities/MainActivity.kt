package com.grandpa.marvelapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration.VERTICAL
import com.grandpa.marvelapp.R
import com.grandpa.marvelapp.adapters.CharacterAdapter
import com.grandpa.marvelapp.viewModel.CharacterViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeNewSplash()
        setContentView(R.layout.activity_main)
        setupUI()


    }

    private fun setupUI() {
        val recyclerView: RecyclerView = findViewById(R.id.charRecyclerView)
        val characterViewModel = ViewModelProvider(this)[CharacterViewModel::class.java]

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decoration)
            characterAdapter = CharacterAdapter()
            adapter = characterAdapter
        }


        val disposable = characterViewModel.getCharactersRemote().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                if (list != null) {
                    characterAdapter.characterListData = list.toMutableList()
                    characterAdapter.notifyDataSetChanged() // this should be changed for improving performence on high traffic
                }
            }, {
                throw Exception().fillInStackTrace()
            })

//            .subscribe {
//                Log.wtf("COUNT",it.size.toString())
//                if (it != null) {
//                    characterAdapter.characterListData = it.toMutableList()
//                    characterAdapter.notifyDataSetChanged() // this should be changed for improving performence on high traffic
//                }
//
//            }

    }


    private fun initializeNewSplash() {
        val splash = installSplashScreen()

    }

}