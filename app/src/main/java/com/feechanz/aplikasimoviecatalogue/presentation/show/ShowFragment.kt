package com.feechanz.aplikasimoviecatalogue.presentation.show


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.feechanz.aplikasimoviecatalogue.R
import com.feechanz.aplikasimoviecatalogue.adapter.MovieListViewAdapter
import com.feechanz.aplikasimoviecatalogue.data.ShowRepository
import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow
import com.feechanz.aplikasimoviecatalogue.presentation.MovieDetailActivity

/**
 * A simple [Fragment] subclass.
 */
class ShowFragment : Fragment(), ShowContract.View {
    private lateinit var adapter: MovieListViewAdapter
    private lateinit var presenter: ShowContract.Presenter

    private lateinit var rvShows: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show, container, false)
        setup(view)
        return view
    }

    private fun setup(view: View) {
        initView(view)
        initPresenter()
        initRecyclerView()
        initData()
    }

    private fun initView(view: View){
        rvShows = view.findViewById(R.id.rvShows)
    }

    private fun initPresenter(){
        presenter = ShowPresenter(this, ShowRepository(context))
    }

    private fun initRecyclerView(){
        adapter = MovieListViewAdapter()
        rvShows.setHasFixedSize(true)
        rvShows.layoutManager = LinearLayoutManager(context)
        rvShows.adapter = adapter

        adapter.onMovieClick = object : MovieListViewAdapter.OnMovieClick {
            override fun onClick(show: MovieShow) {
                val intent = Intent(context, MovieDetailActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun initData(){
        presenter.loadShow()
    }

    override fun showShows(shows: ArrayList<MovieShow>) {
        adapter.addAll(shows)
    }
}
