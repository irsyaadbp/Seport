package com.irsyaad.dicodingsubmission.seport.view.main

import android.os.Build
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.adapter.SportAdapter
import com.irsyaad.dicodingsubmission.seport.view.favorite.FavoriteActivity
import com.irsyaad.dicodingsubmission.seport.view.search.SearchActivity
import com.irsyaad.dicodingsubmission.seport.view.search.SearchOptionsActivity
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainUI(private val sportAdapter: SportAdapter): AnkoComponent<MainActivity>, AnkoLogger {
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        linearLayout {
            lparams{
                width = matchParent
                height = matchParent
                orientation = LinearLayout.VERTICAL
            }
            toolbar {
                backgroundColor = ContextCompat.getColor(ctx, R.color.colorWhite)
                setTitleTextColor(ContextCompat.getColor(ctx, R.color.colorPrimaryDark))
                title = resources.getString(R.string.app_name)

                menu.apply {
                    add("Favorite").apply {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            tooltipText = "Favorite Action"
                        }
                        id = R.id.btnFavoriteMain

                        setIcon(R.drawable.ic_favorite_black_24dp)
                        setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

                        setOnMenuItemClickListener {
                            startActivity<FavoriteActivity>()
                            true
                        }
                    }
                    add("Search").apply {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            tooltipText = "Search Action"
                        }
                        id = R.id.btnSearchMain

                        setIcon(R.drawable.ic_search)
                        setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

                        setOnMenuItemClickListener {
                            startActivity<SearchOptionsActivity>()
                            true
                        }
                    }
                }
            }.lparams {
                width = matchParent
                height = wrapContent
            }
            recyclerView {
                topPadding = dip(8)
                bottomPadding = dip(8)
                leftPadding = dip(16)
                rightPadding = dip(8)
                layoutManager = GridLayoutManager(ctx, 2)
                itemAnimator = SlideInUpAnimator()
                bottom

                adapter = sportAdapter
            }


        }
    }

}