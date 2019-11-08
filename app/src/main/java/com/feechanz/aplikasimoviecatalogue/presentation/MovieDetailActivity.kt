package com.feechanz.aplikasimoviecatalogue.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import com.feechanz.aplikasimoviecatalogue.R
import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow
import com.feechanz.aplikasimoviecatalogue.utils.Constant
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    companion object {
        const val MOVIE_KEY = "movie_key"
    }

    lateinit var movie: MovieShow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        initActionBar()
        initData()
    }

    private fun initActionBar(){
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initData(){
        movie = intent.getParcelableExtra(MOVIE_KEY) as MovieShow
        
        Picasso.get()
            .load(Constant.getPictureFullPath() + movie.posterPath)
            .into(photoImageView)
        titleTextView.text = movie.title
        var kindOf = getString(R.string.movie)
        if(!movie.isMovie){
            kindOf = getString(R.string.show)
        }
        kindOfTextView.text = getString(R.string.kind_of, kindOf)
        releaseDateTextView.text = getString(R.string.release_date, movie.releaseDate)
        voteAverageTextView.text = getString(R.string.vote_average, movie.voteAverage)
        descriptionTextView.text = movie.overview
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}
