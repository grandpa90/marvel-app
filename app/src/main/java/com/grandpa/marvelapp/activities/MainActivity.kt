package com.grandpa.marvelapp.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
    lateinit var characterViewModel: CharacterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeNewSplash()
        setContentView(R.layout.activity_main)
        setupUI()


    }

    private fun setupUI() {
        val recyclerView: RecyclerView = findViewById(R.id.charRecyclerView)
        characterViewModel = ViewModelProvider(this)[CharacterViewModel::class.java]

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


        characterAdapter.setOnItemClickListener(object : CharacterAdapter.onItemClickListener {
            override fun onItemClick(postion: Int) {
                Log.wtf("POSITION", postion.toString())
                val disposable = characterViewModel.getCharacters().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        val id = it[postion]._id // either passed by rx or saved into sharedPrefs...
                        val prefs: SharedPreferences = getSharedPreferences(
                            "com.grandpa.marvelapp", Context.MODE_PRIVATE
                        )

                        prefs.edit().putLong("characterId", id).apply()
                        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                        startActivity(intent)


                    }, {
                        throw Exception().fillInStackTrace()
                    })
            }
        })
        // we need to add listener for recycler view

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