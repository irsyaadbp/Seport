package com.irsyaad.dicodingsubmission.seport.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.model.response.StandingTable
import kotlinx.android.synthetic.main.layout_standing_item.view.*

class StandingAdapter(private val context: Context) : RecyclerView.Adapter<StandingAdapter.ViewHolder>() {
    private var datas: List<StandingTable> = listOf()

    fun setData(mData: List<StandingTable>){
        Log.d("mData", "mData")
        datas = mData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.layout_standing_item,
                parent,
                false
            ))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position], context, position)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val tvIndex = view.tvIndex
        private val tvClubName = view.tvClubName
        private val tvPlayed = view.tvPlayed
        private val tvWin = view.tvWin
        private val tvDraw = view.tvDraw
        private val tvLose = view.tvLose
        private val tvGA = view.tvGA
        private val tvGF = view.tvGF
        private val tvGD = view.tvGD
        private val tvTotal = view.tvTotal

        fun bind(data: StandingTable, context: Context, position: Int){
            tvIndex.text = "${position + 1}"
            tvClubName.text = data.name
            tvPlayed.text = "${data.played}"
            tvWin.text = "${data.win}"
            tvDraw.text = "${data.draw}"
            tvLose.text = "${data.loss}"
            tvGA.text = "${data.goalsagainst}"
            tvGF.text = "${data.goalsfor}"
            tvGD.text = "${data.goalsdifference}"
            tvTotal.text = "${data.total}"
        }
    }
}