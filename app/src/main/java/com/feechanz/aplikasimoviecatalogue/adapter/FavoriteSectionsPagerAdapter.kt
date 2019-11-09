package com.feechanz.aplikasimoviecatalogue.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.feechanz.aplikasimoviecatalogue.R
import com.feechanz.aplikasimoviecatalogue.presentation.favorite.movie.MovieFavoriteFragment
import com.feechanz.aplikasimoviecatalogue.presentation.favorite.show.ShowFavoriteFragment

/**
 * Created by Feechan on 11/9/2019.
 */
class FavoriteSectionsPagerAdapter(private val mContext: Context?, fm: FragmentManager?) :
    FragmentPagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.movie, R.string.show)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MovieFavoriteFragment()
            1 -> fragment = ShowFavoriteFragment()
        }
        return fragment as Fragment
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext?.resources?.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}