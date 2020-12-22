package com.example.androidlivedata.ui

import android.view.View
import com.example.androidlivedata.data.network.responses.Movie

interface RecyclerViewListClickInterface {
    fun onMovieItemClick(view: View, movie: Movie)
}