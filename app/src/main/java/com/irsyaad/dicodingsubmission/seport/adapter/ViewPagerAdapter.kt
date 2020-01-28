package com.irsyaad.dicodingsubmission.seport.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.view.detail.fragment.NextEventFragment
import com.irsyaad.dicodingsubmission.seport.view.detail.fragment.PastEventFragment
import com.irsyaad.dicodingsubmission.seport.view.favorite.fragment.NextFavoriteFragment
import com.irsyaad.dicodingsubmission.seport.view.favorite.fragment.PastFavoriteFragment
import com.irsyaad.dicodingsubmission.seport.view.detail.fragment.StandingFragment
import com.irsyaad.dicodingsubmission.seport.view.detail.fragment.TeamFragment
import com.irsyaad.dicodingsubmission.seport.view.detail.fragment.team.PlayerFragment
import com.irsyaad.dicodingsubmission.seport.view.detail.fragment.team.TeamDetailFragment
import com.irsyaad.dicodingsubmission.seport.view.favorite.fragment.TeamFavoriteFragment

class ViewPagerAdapter(private val context : Context, fragmentManager : FragmentManager, private val from: String) : FragmentPagerAdapter (fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

//    private val pages = if(from == "DetailLeague") listOf(
//        NextEventFragment(),
//        PastEventFragment(),
//        StandingFragment(),
//        TeamFragment()
//    ) else listOf(
//        NextFavoriteFragment(),
//        PastFavoriteFragment()
//    )

    private val pages = when(from) {
        "DetailLeague" -> listOf(
            NextEventFragment(),
            PastEventFragment(),
            StandingFragment(),
            TeamFragment()
        )
        "Favorite" -> listOf(
            NextFavoriteFragment(),
            PastFavoriteFragment(),
            TeamFavoriteFragment()
        )
        "Team" -> listOf(
            TeamDetailFragment(),
            PlayerFragment()
        )
        else -> listOf()
    }


    override fun getItem(position: Int): Fragment {
        return pages[position]
        }

    override fun getCount(): Int {
        return pages.size
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return when(from) {
            "DetailLeague" -> when(position){
                0 -> context.getString(R.string.nextMatch)
                1 -> context.getString(R.string.lastMatch)
                2 -> "Standing"
                else -> "Team"
            }
            "Favorite" -> when(position){
                0 -> context.getString(R.string.nextMatch)
                1 -> context.getString(R.string.lastMatch)
                else -> "Team"
            }
            "Team" -> when (position) {
                0 -> "Team Detail"
                else -> "Players"
            }

            else -> ""
        }
    }
}
