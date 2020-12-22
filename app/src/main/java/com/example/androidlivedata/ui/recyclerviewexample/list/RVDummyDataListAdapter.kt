package com.example.androidlivedata.ui.recyclerviewexample.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidlivedata.R
import com.example.androidlivedata.data.network.responses.Movie

class RVDummyDataListAdapter(private val listener: RVDummyDataListClickInterface) : RecyclerView.Adapter<RVDummyDataListAdapter.MyViewHolder>() {
    private var movies = mutableListOf<Movie>()

    fun addMovies(_movies: List<Movie>) {
        movies.addAll(_movies)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPoster = itemView.findViewById<ImageView>(R.id.iv_movie_poster)
        val tvName = itemView.findViewById<TextView>(R.id.tv_movie_name)
        val tvRating = itemView.findViewById<TextView>(R.id.tv_rating)
        val tvDuration = itemView.findViewById<TextView>(R.id.tv_duration)
        val tvDirectors = itemView.findViewById<TextView>(R.id.tv_directors)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: RVDummyDataListAdapter.MyViewHolder, position: Int) {
        val movie: Movie = movies[position]
        Glide.with(holder.tvPoster).load(movie.posterUrl).into(holder.tvPoster)
        holder.tvName.text = movie.name
        holder.tvRating.text = movie.rating.toString()
        holder.tvDuration.text = movie.duration
        holder.tvDirectors.text = movie.directors

        holder.tvPoster.setOnClickListener {
            listener.onMovieItemClick(it, movies[position])
        }
    }

    override fun getItemCount() = movies.size
}