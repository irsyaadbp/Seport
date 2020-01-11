package com.irsyaad.dicodingsubmission.seport.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.view.detail.fragment.NextEventFragment
import com.irsyaad.dicodingsubmission.seport.view.detail.fragment.PastEventFragment

class ViewPagerAdapter(private val context : Context, fragmentManager : FragmentManager) : FragmentPagerAdapter (fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pages = listOf(
        NextEventFragment(),
        PastEventFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
        }

    override fun getCount(): Int {
        return pages.size
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> context.getString(R.string.nextMatch)
            else -> context.getString(R.string.lastMatch)
        }
    }
}
