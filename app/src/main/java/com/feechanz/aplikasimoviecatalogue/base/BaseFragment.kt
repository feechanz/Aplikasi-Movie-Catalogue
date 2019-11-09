package com.feechanz.aplikasimoviecatalogue.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment

/**
 * Created by Feechan on 11/8/2019.
 */
abstract class BaseFragment : Fragment() {
    protected var progressBar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getContentView(), container, false)
        setup(view)
        return view
    }

    abstract fun getContentView(): Int
    abstract fun setup(view: View)

    fun showLoadingBar() {
        progressBar?.visibility = View.VISIBLE
    }

    fun hideLoadingBar() {
        progressBar?.visibility = View.GONE
    }
}