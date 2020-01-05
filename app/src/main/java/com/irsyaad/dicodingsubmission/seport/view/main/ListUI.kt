package com.irsyaad.dicodingsubmission.seport.view.main

import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ListUI: AnkoComponent<ViewGroup> {
    companion object {
        const val vlContainer = 1
        const val ivBadge = 2
        const val tvTitleLeague = 3
    }
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
        cardView {
            lparams {
                width = matchParent
                height = wrapContent
                margin = dip(8)
            }
            verticalLayout {
                id = vlContainer
                isClickable = true
                isFocusable = true
                padding = dip(16)

                hover()

                imageView {
                    id = ivBadge
                }.lparams {
                    width = dip(75)
                    height = dip(75)
                    gravity = Gravity.CENTER
                }
                textView {
                    id = tvTitleLeague
                    textSize = 18f
                    textColor = ContextCompat.getColor(context, android.R.color.black)
                    typeface = Typeface.DEFAULT_BOLD
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }.lparams {
                    topMargin = dip(16)
                    width = wrapContent
                    height = wrapContent
                    gravity = Gravity.CENTER
                }
            }.lparams {
                width = matchParent
                height = wrapContent
            }
        }
    }

    private fun View.hover() = with(TypedValue()) {
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
        setBackgroundResource(resourceId)
    }
}