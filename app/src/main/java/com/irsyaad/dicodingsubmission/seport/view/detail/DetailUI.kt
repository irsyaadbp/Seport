package com.irsyaad.dicodingsubmission.seport.view.detail

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.model.Sport
import org.jetbrains.anko.*

class DetailUI(private val data: Sport?): AnkoComponent<DetailActivity> {
    companion object{
        const val ivBadge = 1
        const val tvTitle = 2
        const val tvDesc = 3
    }
    override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
        scrollView {
            relativeLayout {
                imageView {
                    backgroundColor = Color.DKGRAY
                }.lparams(width = matchParent, height = dip(200))

                imageView {
                    id = ivBadge
                    setImageResource(data?.badge ?: R.drawable.english_premier_league)
                }.lparams(width = dip(150), height = dip(150)) {
                    centerHorizontally()
                    topMargin = dip(100)
                }

                textView {
                    id = tvTitle
                    gravity = Gravity.CENTER
                    topPadding = dip(16)
                    text = data?.league ?: "Title"
                    textSize = 24f
                    setTypeface(typeface, Typeface.BOLD)
                }.lparams(width = matchParent) {
                    below(ivBadge)
                }

                textView {
                    id = tvDesc
                    padding = dip(16)
                    text =  data?.description ?: "Description"
                }.lparams(width = matchParent) {
                    below(tvTitle)
                    topMargin = dip(16)
                }


            }
        }
    }
}