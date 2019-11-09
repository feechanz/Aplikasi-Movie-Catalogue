package com.feechanz.aplikasimoviecatalogue.presentation.favorite

import android.view.View
import androidx.viewpager.widget.ViewPager
import com.feechanz.aplikasimoviecatalogue.R
import com.feechanz.aplikasimoviecatalogue.adapter.FavoriteSectionsPagerAdapter
import com.feechanz.aplikasimoviecatalogue.base.BaseFragment
import com.google.android.material.tabs.TabLayout

/**
 * Created by Feechan on 11/9/2019.
 */
class FavoriteFragment : BaseFragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    override fun getContentView(): Int {
        return R.layout.fragment_favorite
    }

    override fun setup(view: View) {
        initView(view)
        initTabLayout()
    }

    private fun initView(view: View) {
        viewPager = view.findViewById(R.id.view_pager)
        tabLayout = view.findViewById(R.id.tabs)
    }


    private fun initTabLayout() {
        val sectionsPagerAdapter = FavoriteSectionsPagerAdapter(context, childFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}