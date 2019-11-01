package com.feechanz.aplikasimoviecatalogue.data

import android.content.Context
import com.feechanz.aplikasimoviecatalogue.R
import com.feechanz.aplikasimoviecatalogue.model.Movie

/**
 * Created by Feechan on 11/1/2019.
 */
class ShowRepository(val context: Context?){
    fun getShows() : ArrayList<Movie> {
        val shows = arrayListOf<Movie>()
        shows.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BZGJjYzhjYTYtMDBjYy00OWU1LTg5OTYtNmYwOTZmZjE3ZDdhXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_UY209_CR3,0,140,209_AL_.jpg",
                "The Sopranos",
                context?.getString(R.string.show1),
                false
            )
        )

        shows.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BMjA5NzA5NjMwNl5BMl5BanBnXkFtZTgwNjg2OTk2NzM@._V1_UY209_CR0,0,140,209_AL_.jpg",
                "Game of Thrones",
                context?.getString(R.string.show2),
                false
            )
        )

        shows.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BNjc1NzYwODEyMV5BMl5BanBnXkFtZTcwNTcxMzU1MQ@@._V1_UY209_CR7,0,140,209_AL_.jpg",
                "The Wire",
                context?.getString(R.string.show3),
                false
            )
        )

        shows.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BMTM5MjkwMTI0MV5BMl5BanBnXkFtZTcwODQwMTc0OQ@@._V1_UY209_CR7,0,140,209_AL_.jpg",
                "Dexter",
                context?.getString(R.string.show4),
                false
            )
        )

        shows.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BMjhiMzgxZTctNDc1Ni00OTIxLTlhMTYtZTA3ZWFkODRkNmE2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UY209_CR5,0,140,209_AL_.jpg",
                "Breaking Bad",
                context?.getString(R.string.show5),
                false
            )
        )

        shows.add(
            Movie(
                "https://m.media-amazon.com/images/M/MV5BYWY4ODJiZjMtNWMxYi00ZmM5LWIwZmQtZWY0MjJmZGU5MjcxXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UY209_CR0,0,140,209_AL_.jpg",
                "The Walking Dead",
                context?.getString(R.string.show6),
                false
            )
        )

        shows.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BYTYxNzc5ZmYtODcyNi00ZWUwLWExNWUtY2Y0YTlhZDBlMGU1XkEyXkFqcGdeQXVyNzQ1ODk3MTQ@._V1_UY209_CR2,0,140,209_AL_.jpg",
                "Boardwalk Empire",
                context?.getString(R.string.show7),
                false
            )
        )

        shows.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BZTM4NjBkMWEtZDA4My00MzA0LWI0ZWMtNzUwYWVhOThiMTgwXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_UY209_CR9,0,140,209_AL_.jpg",
                "Rome",
                context?.getString(R.string.show8),
                false
            )
        )

        shows.add(
            Movie(
                "https://m.media-amazon" +
                        ".com/images/M/MV5BMjMwNzk5Nzg2OV5BMl5BanBnXkFtZTgwMjg1OTk1NDE@._V1_UY209_CR1,0,140,209_AL_.jpg",
                "Mad Men",
                context?.getString(R.string.show9),
                false
            )
        )

        shows.add(
            Movie(
                "https://m.media-amazon.com/images/M/MV5BMTEyODg2NzkwMDBeQTJeQWpwZ15BbWU4MDQwODI3MzIx._V1_UY209_CR0,0,140,209_AL_.jpg",
                "Sons of Anarchy",
                context?.getString(R.string.show10),
                false
            )
        )
        return shows
    }
}