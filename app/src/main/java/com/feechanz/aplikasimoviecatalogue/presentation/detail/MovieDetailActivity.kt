package com.feechanz.aplikasimoviecatalogue.presentation.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.feechanz.aplikasimoviecatalogue.R
import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow
import com.feechanz.aplikasimoviecatalogue.data.model.MovieShowDetail
import com.feechanz.aplikasimoviecatalogue.utils.Constant
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    companion object {
        const val MOVIE_KEY = "movie_key"
    }

    lateinit var movie: MovieShow
    private lateinit var movieViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        initActionBar()
        initData()
        initViewModel()
    }

    private fun initViewModel() {
        showLoadingBar()
        movieViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MovieDetailViewModel::class.java)
        movieViewModel.getMovieShow(
            movie.id,
            movie.isMovie,
            getString(R.string.language_code)
        ).observe(this, Observer { movie ->
            hideLoadingBar()
            if (movie != null) {
                setMovieDetail(movie)
            }
        })
        movieViewModel.getErrorMessage().observe(this, Observer { errorMessage ->
            hideLoadingBar()
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showLoadingBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoadingBar() {
        progressBar.visibility = View.GONE
    }

    private fun initActionBar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initData() {
        movie = intent.getParcelableExtra(MOVIE_KEY) as MovieShow
    }

    private fun setMovieDetail(movieDetail: MovieShowDetail) {
        Picasso.get()
            .load(Constant.getPictureFullPath() + movieDetail.posterPath)
            .into(photoImageView)
        titleTextView.text = movieDetail.title
        var kindOf = getString(R.string.movie)
        if (!movieDetail.isMovie) {
            kindOf = getString(R.string.show)
        }
        statusTextView.text = getString(R.string.status, movieDetail.status)
        kindOfTextView.text = getString(R.string.kind_of, kindOf)
        releaseDateTextView.text = getString(R.string.release_date, movieDetail.releaseDate)
        voteAverageTextView.text = getString(R.string.vote_average, movieDetail.voteAverage)
        descriptionTextView.text = movieDetail.overview
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}
