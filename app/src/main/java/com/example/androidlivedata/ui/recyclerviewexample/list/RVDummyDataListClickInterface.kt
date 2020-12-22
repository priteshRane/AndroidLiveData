package com.example.androidlivedata.ui.recyclerviewexample.list

import android.view.View
import com.example.androidlivedata.data.network.responses.Movie

interface RVDummyDataListClickInterface {
    fun onMovieItemClick(view: View, movie: Movie)
}