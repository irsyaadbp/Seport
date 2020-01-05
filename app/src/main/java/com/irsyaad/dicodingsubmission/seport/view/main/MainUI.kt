package com.irsyaad.dicodingsubmission.seport.view.main

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.irsyaad.dicodingsubmission.seport.adapter.SportAdapter
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainUI(private val sportAdapter: SportAdapter): AnkoComponent<MainActivity>, AnkoLogger {
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        frameLayout { 
            lparams{
                width = matchParent
                height = matchParent
            }
            recyclerView {
                topPadding = dip(8)
                bottomPadding = dip(8)
                leftPadding = dip(16)
                rightPadding = dip(8)
                layoutManager = GridLayoutManager(ctx, 2)
                itemAnimator = SlideInUpAnimator()

                adapter = sportAdapter
            }


        }
    }

}