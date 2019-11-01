package com.feechanz.aplikasimoviecatalogue.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import com.feechanz.aplikasimoviecatalogue.R
import com.feechanz.aplikasimoviecatalogue.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    companion object {
        const val MOVIE_KEY = "movie_key"
    }

    lateinit var movie: Movie

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
        movie = intent.getParcelableExtra(MOVIE_KEY) as Movie
        Picasso.get().load(movie.photoUrl).into(photoImageView)
        titleTextView.text = movie.name
        var kindOf = getString(R.string.show)
        if(movie.isMovie) kindOf = getString(R.string.movie)

        kindOfTextView.text = getString(R.string.kind_of, kindOf)
        descriptionTextView.text = movie.description
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}
