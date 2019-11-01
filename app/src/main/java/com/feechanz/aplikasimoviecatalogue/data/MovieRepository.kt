package com.feechanz.aplikasimoviecatalogue.data

import android.content.Context
import com.feechanz.aplikasimoviecatalogue.R
import com.feechanz.aplikasimoviecatalogue.model.Movie

/**
 * Created by Feechan on 11/1/2019.
 */
class MovieRepository(val context: Context?){

    fun getMovies() : ArrayList<Movie> {
        val movies = arrayListOf<Movie>()
        movies.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BMjMxNjY2MDU1OV5BMl5BanBnXkFtZTgwNzY1MTUwNTM@._V1_UY209_CR0,0,140,209_AL_.jpg",
                "Avengers: Infinity War",
                context?.getString(R.string.movie1),
                true
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BMTg1MTY2MjYzNV5BMl5BanBnXkFtZTgwMTc4NTMwNDI@._V1_UY209_CR0,0,140,209_AL_.jpg",
                "Black Panther",
                context?.getString(R.string.movie2),
                true
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BNjk1Njk3YjctMmMyYS00Y2I4LThhMzktN2U0MTMyZTFlYWQ5XkEyXkFqcGdeQXVyODM2ODEzMDA@._V1_UY209_CR34,0,140,209_AL_.jpg",
                "Deadpool 2",
                context?.getString(R.string.movie3),
                true
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BNzIxMjYwNDEwN15BMl5BanBnXkFtZTgwMzk5MDI3NTM@._V1_UY209_CR0,0,140,209_AL_.jpg",
                "Jurassic World: Fallen Kingdom",
                context?.getString(R.string.movie4),
                true
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BMTAxMGRmODYtM2NkYS00ZGRlLWE1MWItYjI1MzIwNjQwN2RiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UY209_CR0,0,140,209_AL_.jpg",
                "The Meg",
                context?.getString(R.string.movie5),
                true
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BNzAwNzUzNjY4MV5BMl5BanBnXkFtZTgwMTQ5MzM0NjM@._V1_UY209_CR0,0,140,209_AL_.jpg",
                "Venom",
                context?.getString(R.string.movie6),
                true
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BNjRlZmM0ODktY2RjNS00ZDdjLWJhZGYtNDljNWZkMGM5MTg0XkEyXkFqcGdeQXVyNjAwMjI5MDk@._V1_UX140_CR0,0,140,209_AL_.jpg",
                "Mission: Impossible - Fallout",
                context?.getString(R.string.movie7),
                true
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BY2JiYTNmZTctYTQ1OC00YjU4LWEwMjYtZjkwY2Y5MDI0OTU3XkEyXkFqcGdeQXVyNTI4MzE4MDU@._V1_UY209_CR0,0,140,209_AL_.jpg",
                "Ready Player One",
                context?.getString(R.string.movie8),
                true
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BMTEzNzY0OTg0NTdeQTJeQWpwZ15BbWU4MDU3OTg3MjUz._V1_UY209_CR0,0,140,209_AL_.jpg",
                "Incredibles 2",
                context?.getString(R.string.movie9),
                true
            )
        )

        movies.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BMTA2NDc3Njg5NDVeQTJeQWpwZ15BbWU4MDc1NDcxNTUz._V1_UY209_CR0,0,140,209_AL_.jpg",
                "Bohemian Rhapsody",
                context?.getString(R.string.movie10),
                true
            )
        )
        return movies
    }
}