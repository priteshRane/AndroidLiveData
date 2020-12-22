package com.example.androidlivedata

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidlivedata.data.network.responses.Movie
import com.example.androidlivedata.data.repositories.MovieRepository
import com.example.androidlivedata.ui.recyclerviewexample.list.RVDummyDataListInterface
import com.example.androidlivedata.util.Coroutines
import com.example.androidlivedata.util.NoInternetException
import kotlinx.coroutines.delay

class MainViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val TAG = "MovieListViewModel"
    var recyclerViewListInterface: RVDummyDataListInterface? = null

    // For String
    var dataString: MutableLiveData<String> = MutableLiveData<String>()

    // For Dummy data
    private val _dataMutableList = MutableLiveData<MutableList<Movie>>()
    val dataMutableList: LiveData<MutableList<Movie>>
        get() = _dataMutableList

    private val movie1 = Movie(_id = "5fac16d900926d7f61681d91", name = "The Shawshank Redemption", year = 1994, rating = 9.3, yourRating = 0, genres = "Drama", description = "Demo description of movie 1", duration = "2h 22min", directors = "Frank Darabont", writers = "Stephen King, Frank Darabont", stars = "Tim Robbins", posterUrl = "https://firebasestorage.googleapis.com/v0/b/testapis-286008.appspot.com/o/the-shawshank-redemption-poster.jpg?alt=media&token=0b945c32-4d53-4eff-a389-46c019eab958", createdAt = "2020-11-11T16:52:41.756Z", updatedAt = "2020-11-11T16:52:41.756Z", _v = null)
    private val movie2 = Movie(_id = "5fac29be5e1f6acdb7a7bb89", name = "The Godfather", year = 1972, rating = 9.2, yourRating = 0, genres = "Crime, Drama", description = "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", duration = "2h 22min", directors = "Francis Ford Coppola", writers = "Mario Puzo, Francis Ford Coppola", stars = "Marlon Brando", posterUrl = "https://firebasestorage.googleapis.com/v0/b/testapis-286008.appspot.com/o/the-godfather-poster.jpg?alt=media&token=043370cc-be67-4f59-8a69-879ab517c86c", createdAt = "2020-11-11T18:13:18.964Z", updatedAt = "2020-11-11T18:13:18.964Z", _v = null)
    private val movie3 = Movie(_id = "5fac2a305e1f6a4faca7bb8a", name = "The Godfather: Part II", year = 1974, rating = 9.0, yourRating = 0, genres = "Crime, Drama", description = "The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.", duration = "3h 22min", directors = "Francis Ford Coppola", writers = "Mario Puzo, Francis Ford Coppola", stars = "Al Pacino", posterUrl = "https://firebasestorage.googleapis.com/v0/b/testapis-286008.appspot.com/o/the-godfather-part-ii-movie-poster.jpg?alt=media&token=4a0a4fc8-3597-4752-9e22-9e4ad1066104", createdAt = "2020-11-11T18:15:12.230Z", updatedAt = "2020-11-11T18:15:12.230Z", _v = null)
    private val movie4 = Movie(_id = "5fac2ae75e1f6a11b4a7bb8b", name = "The Dark Knight", year = 2008, rating = 9.0, yourRating = 0, genres = "Action, Crime, Drama", description = "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", duration = "3h 32min", directors = "Christopher Nolan", writers = "Jonathan Nolan", stars = "Christian Bale, Heath Ledger", posterUrl = "https://firebasestorage.googleapis.com/v0/b/testapis-286008.appspot.com/o/the-dark-knight.jpg?alt=media&token=91702ff2-950c-410e-8c2b-3dc2ece72ea6", createdAt = "2020-11-11T18:18:15.854Z", updatedAt = "2020-11-11T18:18:15.854Z", _v = null)
    private val movie5 = Movie(_id = "5fac2be65e1f6aa68ea7bb8c", name = "12 Angry Men", year = 1957, rating = 9.0, yourRating = 0, genres = "Crime, Drama", description = "A jury holdout attempts to prevent a miscarriage of justice by forcing his colleagues to reconsider the evidence.", duration = "1h 36min", directors = "Sidney Lumet", writers = "Reginald Rose, Reginald Rose", stars = "Henry Fonda", posterUrl = "https://firebasestorage.googleapis.com/v0/b/testapis-286008.appspot.com/o/12-angry-men-theatrical-movie-poster.jpg?alt=media&token=beb6cd2a-9a17-4be5-8a21-5b391b004e25", createdAt = "2020-11-11T18:22:30.956Z", updatedAt = "2020-11-11T18:22:30.956Z", _v = null)
    private val movie6 = Movie(_id = "5fac2c805e1f6a284da7bb8d", name = "Schindler's List", year = 1993, rating = 8.9, yourRating = 0, genres = "Biography, Drama, History", description = "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.", duration = "3h 15min", directors = "Steven Spielberg", writers = "Thomas Keneally, Steven Zaillian", stars = "Liam Neeson", posterUrl = "https://firebasestorage.googleapis.com/v0/b/testapis-286008.appspot.com/o/schindlers-list-poster.jpg?alt=media&token=febcbd2a-2a1b-4f48-ae96-928619de38fa", createdAt = "2020-11-11T18:25:04.303Z", updatedAt = "2020-11-11T18:25:04.303Z", _v = null)
    private val movie7 = Movie(_id = "5fac2d365e1f6aa950a7bb8e", name = "The Lord of the Rings: The Return of the King", year = 2003, rating = 8.9, yourRating = 0, genres = "Action, Adventure, Drama", description = "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.", duration = "3h 21min", directors = "Peter Jackson", writers = "J.R.R. Tolkien, Fran Walsh", stars = "Elijah Wood", posterUrl = "https://firebasestorage.googleapis.com/v0/b/testapis-286008.appspot.com/o/lord-of-the-rings-the-return-of-the-king.jpg?alt=media&token=909f2c65-1630-45d9-9337-e3ed6195ddee", createdAt = "2020-11-11T18:28:06.956Z", updatedAt = "2020-11-11T18:28:06.956Z", _v = null)
    private val movie8 = Movie(_id = "5fac2dce5e1f6aedbfa7bb8f", name = "Pulp Fiction", year = 1994, rating = 8.9, yourRating = 0, genres = "Crime, Drama", description = "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.", duration = "2h 34min", directors = "Quentin Tarantino", writers = "Quentin Tarantino, Roger Avary", stars = "John Travolta", posterUrl = "https://firebasestorage.googleapis.com/v0/b/testapis-286008.appspot.com/o/pulp-fiction-poster.jpg?alt=media&token=d80a1d08-de04-4dda-af59-7ebd6b7549e7", createdAt = "2020-11-11T18:30:38.305Z", updatedAt = "2020-11-11T18:30:38.305Z", _v = null)
    private val movie9 = Movie(_id = "5fac2f625e1f6ad77fa7bb90", name = "Fight Club", year = 1999, rating = 8.8, yourRating = 0, genres = "Drama", description = "An insomniac office worker and a devil-may-care soapmaker form an underground fight club that evolves into something much, much more.", duration = "2h 19min", directors = "David Fincher", writers = "Chuck Palahniuk, Jim Uhls", stars = "Brad Pitt", posterUrl = "https://firebasestorage.googleapis.com/v0/b/testapis-286008.appspot.com/o/fight-club.jpg?alt=media&token=f1af0b25-d83f-4302-a940-76510381482e", createdAt = "2020-11-11T18:37:22.556Z", updatedAt = "2020-11-11T18:37:22.556Z", _v = null)
    private val movie10 = Movie(_id = "5fac2fe75e1f6a6200a7bb91", name = "Forrest Gump", year = 1994, rating = 8.8, yourRating = 0, genres = "Drama, Romance", description = "The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate and other historical events unfold through the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood sweetheart. ", duration = "2h 22min", directors = "Robert Zemeckis", writers = "Winston Groom, Eric Roth", stars = "Tom Hanks", posterUrl = "https://firebasestorage.googleapis.com/v0/b/testapis-286008.appspot.com/o/forrest-gump.jpg?alt=media&token=18a48709-a172-4dbc-a661-80447a102cb0", createdAt = "2020-11-11T18:39:35.975Z", updatedAt = "2020-11-11T18:39:35.975Z", _v = null)

    // For API data
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    fun setDataString(value: String) {
        dataString.postValue(value)
    }

    fun getDataString(): LiveData<String> {
        return dataString
    }

    fun getDataMutableList() {
        Log.i(TAG, "Get Movies")
        if (dataMutableList.value.isNullOrEmpty()) {
            getDataMutableListFormDummyData()
        }
    }

    fun getDataMutableListFormDummyData() {
        Coroutines.main {
            try {
                recyclerViewListInterface?.showProgressBar()
                delay(2000)

                val moviesAre = mutableListOf<Movie>()
                moviesAre.add(movie1)
                moviesAre.add(movie2)
                moviesAre.add(movie3)
                moviesAre.add(movie4)
                moviesAre.add(movie5)
                moviesAre.add(movie6)
                moviesAre.add(movie7)
                moviesAre.add(movie8)
                moviesAre.add(movie9)
                moviesAre.add(movie10)

                _dataMutableList.postValue(moviesAre)
                recyclerViewListInterface?.hideProgressBar()
            } catch (e: Exception) {
                Log.i(TAG, e.toString())
            }
        }
    }

    fun deleteElementFromDataMutableList(movie: Movie) {
        val indexIs: Int? = dataMutableList.value?.indexOf(movie)
        if (indexIs != null) {
            _dataMutableList.value?.removeAt(indexIs)
        }
    }

    fun changeRatingFromDataMutableList(movie: Movie) {
        val indexIs: Int? = dataMutableList.value?.indexOf(movie)
        if (indexIs != null) {
            _dataMutableList.value?.find { it._id == movie._id  }.let { it?.rating = 1.0 }
        }
    }

    fun getMovieList() {
        Log.i(TAG, "Get Movies")
        if (movies.value.isNullOrEmpty()) {
            getMoviesFromRepository()
        }
    }

    private fun getMoviesFromRepository() {
        Coroutines.main {
            try {
                recyclerViewListInterface?.showProgressBar()
                val movieResponse = movieRepository.getMovies(1, 10)
                if (movieResponse.isSuccessful) {
                    _movies.value = movieResponse.body()?.movie
                }
                recyclerViewListInterface?.hideProgressBar()
            } catch (e: NoInternetException) {
                Log.i(TAG, e.toString())
            } catch (e: Exception) {
                Log.i(TAG, e.toString())
            }
        }
    }
}