package com.feechanz.aplikasimoviecatalogue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.feechanz.aplikasimoviecatalogue.MovieDetailActivity.Companion.MOVIE_KEY
import com.feechanz.aplikasimoviecatalogue.adapter.MovieListViewAdapter
import com.feechanz.aplikasimoviecatalogue.model.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieListViewAdapter.OnMovieClick {
    private lateinit var adapter: MovieListViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListView()
        initData()
    }

    private fun initListView() {
        adapter = MovieListViewAdapter(this)
        adapter.onMovieClick = this
        movieListView.adapter = adapter
    }

    private fun initData() {
        val movies = arrayListOf<Movie>()
        movies.add(
            Movie(
                "https://m.media-amazon.com/images/M/MV5BMjMxNjY2MDU1OV5BMl5BanBnXkFtZTgwNzY1MTUwNTM@._V1_UY209_CR0,0,140,209_AL_.jpg",
                "Avengers: Infinity War",
                "The Avengers and their allies must be willing to sacrifice all in an attempt to defeat the powerful Thanos before his blitz of devastation and ruin puts an end to the universe."
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon.com/images/M/MV5BMTg1MTY2MjYzNV5BMl5BanBnXkFtZTgwMTc4NTMwNDI@._V1_UY209_CR0,0,140,209_AL_.jpg",
                "Black Panther",
                "T'Challa, heir to the hidden but advanced kingdom of Wakanda, must step forward to lead his people into a new future and must confront a challenger from his country's past."
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon.com/images/M/MV5BNjk1Njk3YjctMmMyYS00Y2I4LThhMzktN2U0MTMyZTFlYWQ5XkEyXkFqcGdeQXVyODM2ODEzMDA@._V1_UY209_CR34,0,140,209_AL_.jpg",
                "Deadpool 2",
                "Foul-mouthed mutant mercenary Wade Wilson (a.k.a. Deadpool), brings together a team of fellow mutant rogues to protect a young boy with supernatural abilities from the brutal, time-traveling cyborg Cable."
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon.com/images/M/MV5BNzIxMjYwNDEwN15BMl5BanBnXkFtZTgwMzk5MDI3NTM@._V1_UY209_CR0,0,140,209_AL_.jpg",
                "Jurassic World: Fallen Kingdom",
                "When the island's dormant volcano begins roaring to life, Owen and Claire mount a campaign to rescue the remaining dinosaurs from this extinction-level event."
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon.com/images/M/MV5BMTAxMGRmODYtM2NkYS00ZGRlLWE1MWItYjI1MzIwNjQwN2RiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UY209_CR0,0,140,209_AL_.jpg",
                "The Meg",
                "A group of scientists exploring the Marianas Trench encounter the largest marine predator that has ever existed - the Megalodon."
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon.com/images/M/MV5BNzAwNzUzNjY4MV5BMl5BanBnXkFtZTgwMTQ5MzM0NjM@._V1_UY209_CR0,0,140,209_AL_.jpg",
                "Venom",
                "A failed reporter is bonded to an alien entity, one of many symbiotes who have invaded Earth. But the being takes a liking to Earth and decides to protect it."
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon.com/images/M/MV5BNjRlZmM0ODktY2RjNS00ZDdjLWJhZGYtNDljNWZkMGM5MTg0XkEyXkFqcGdeQXVyNjAwMjI5MDk@._V1_UX140_CR0,0,140,209_AL_.jpg",
                "Mission: Impossible - Fallout",
                "Ethan Hunt and his IMF team, along with some familiar allies, race against time after a mission gone wrong."
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon.com/images/M/MV5BY2JiYTNmZTctYTQ1OC00YjU4LWEwMjYtZjkwY2Y5MDI0OTU3XkEyXkFqcGdeQXVyNTI4MzE4MDU@._V1_UY209_CR0,0,140,209_AL_.jpg",
                "Ready Player One",
                "When the creator of a virtual reality called the OASIS dies, he makes a posthumous challenge to all OASIS users to find his Easter Egg, which will give the finder his fortune and control of his world."
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon.com/images/M/MV5BMTEzNzY0OTg0NTdeQTJeQWpwZ15BbWU4MDU3OTg3MjUz._V1_UY209_CR0,0,140,209_AL_.jpg",
                "Incredibles 2",
                "The Incredibles hero family takes on a new mission, which involves a change in family roles: Bob Parr (Mr Incredible) must manage the house while his wife Helen (Elastigirl) goes out to save the world."
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon.com/images/M/MV5BMTA2NDc3Njg5NDVeQTJeQWpwZ15BbWU4MDc1NDcxNTUz._V1_UY209_CR0,0,140,209_AL_.jpg",
                "Bohemian Rhapsody",
                "The story of the legendary British rock band Queen and lead singer Freddie Mercury, leading up to their famous performance at Live Aid (1985)."
            )
        )

        adapter.movies = movies
    }

    override fun onClick(movie: Movie) {
        val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE_KEY, movie)
        startActivity(intent)
    }
}
