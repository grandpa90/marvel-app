package com.grandpa.marvelapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.grandpa.marvelapp.R
import com.grandpa.marvelapp.adapters.ComicsAdapter
import com.grandpa.marvelapp.adapters.EventsAdapter
import com.grandpa.marvelapp.adapters.SeriesAdapter
import com.grandpa.marvelapp.adapters.StoriesAdapter
import com.grandpa.marvelapp.viewModel.ComicsViewModel
import com.grandpa.marvelapp.viewModel.EventsViewModel
import com.grandpa.marvelapp.viewModel.SeriesViewModel
import com.grandpa.marvelapp.viewModel.StoriesViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailsActivity : AppCompatActivity() {

    var characterId: Long = 0
    lateinit var comicsViewModel: ComicsViewModel
    lateinit var eventsViewModel: EventsViewModel
    lateinit var storiesViewModel: StoriesViewModel
    lateinit var seriesViewModel: SeriesViewModel

    lateinit var comicsAdapter: ComicsAdapter
    lateinit var eventsAdapter: EventsAdapter
    lateinit var seriesAdapter: SeriesAdapter
    lateinit var storiesAdapter: StoriesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setupUI()
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        val prefs: SharedPreferences = getSharedPreferences(
            "com.grandpa.marvelapp", Context.MODE_PRIVATE
        )

        characterId = prefs.getLong("characterId", 0)
        comicsViewModel = ViewModelProvider(this)[ComicsViewModel::class.java]
        eventsViewModel = ViewModelProvider(this)[EventsViewModel::class.java]
        seriesViewModel = ViewModelProvider(this)[SeriesViewModel::class.java]
        storiesViewModel = ViewModelProvider(this)[StoriesViewModel::class.java]

        val comicsRecyclerView: RecyclerView = findViewById(R.id.comicsRecyclerView)
        val eventsRecyclerView: RecyclerView = findViewById(R.id.eventsRecyclerView)
        val seriesRecyclerView: RecyclerView = findViewById(R.id.seriesRecyclerView)
        val storiesRecyclerView: RecyclerView = findViewById(R.id.storiesRecyclerView)

        val characterIdTV: TextView = findViewById(R.id.characterIdTV)
        characterIdTV.text = "ID: $characterId"

        comicsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@DetailsActivity)
            val decoration =
                DividerItemDecoration(applicationContext, MaterialDividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            comicsAdapter = ComicsAdapter()
            adapter = comicsAdapter
        }

        val disposableComics =
            comicsViewModel.getComicsRemote(characterId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    if (list != null && list.isNotEmpty()) {
                        if (list.size > 3) {
                            comicsAdapter.comicsDataList = list.take(3).toMutableList()
                            comicsAdapter.notifyDataSetChanged() // this should be changed for improving performence on high traffic
                        } else {
                            comicsAdapter.comicsDataList = list.toMutableList()
                            comicsAdapter.notifyDataSetChanged()
                        }
                    }
                }, {
                    it.printStackTrace()
                })
        eventsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@DetailsActivity)
            val decoration = DividerItemDecoration(
                applicationContext,
                MaterialDividerItemDecoration.VERTICAL
            )
            addItemDecoration(decoration)
            eventsAdapter = EventsAdapter()
            adapter = eventsAdapter
        }

        val disposableEvent = eventsViewModel.getEventsRemote(characterId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->

                if (list != null && list.isNotEmpty()) {
                    if (list.size > 3) {
                        eventsAdapter.eventsDataList = list.take(3).toMutableList()
                        eventsAdapter.notifyDataSetChanged()
                    } else {
                        eventsAdapter.eventsDataList = list.toMutableList()
                        eventsAdapter.notifyDataSetChanged()
                    }
                }

            }, {
                it.printStackTrace()
            })

        seriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@DetailsActivity)
            val decoration = DividerItemDecoration(
                applicationContext,
                MaterialDividerItemDecoration.VERTICAL
            )
            addItemDecoration(decoration)
            seriesAdapter = SeriesAdapter()
            adapter = seriesAdapter
        }

        val disposableSeries = seriesViewModel.getSeriesRemote(characterId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->

                if (list != null && list.isNotEmpty()) {
                    if (list.size > 3) {
                        seriesAdapter.seriesDataList = list.take(3).toMutableList()
                        seriesAdapter.notifyDataSetChanged()
                    } else {
                        seriesAdapter.seriesDataList = list.toMutableList()
                        seriesAdapter.notifyDataSetChanged()
                    }
                }
            }, {
                it.printStackTrace()
            })

        storiesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@DetailsActivity)
            val decoration = DividerItemDecoration(
                applicationContext,
                MaterialDividerItemDecoration.VERTICAL
            )
            addItemDecoration(decoration)
            storiesAdapter = StoriesAdapter()
            adapter = storiesAdapter
        }


//        val disposableStories = storiesViewModel.getStoriesRemote(characterId)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ list ->
//                if (list != null && list.isNotEmpty())
//                    if (list.size > 3) {
//                        storiesAdapter.storiesListData = list.take(3).toMutableList()
//                        storiesAdapter.notifyDataSetChanged()
//                    } else {
//                        storiesAdapter.storiesListData = list.toMutableList()
//                        storiesAdapter.notifyDataSetChanged()
//                    }
//            }, {
//                throw Exception().fillInStackTrace()
//            })


    }
}