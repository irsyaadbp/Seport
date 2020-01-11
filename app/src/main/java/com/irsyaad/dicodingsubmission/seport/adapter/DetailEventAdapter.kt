package com.irsyaad.dicodingsubmission.seport.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.model.DetailEventModel
import kotlinx.android.synthetic.main.layout_event_item.view.*

class DetailEventAdapter(private val context: Context): RecyclerView.Adapter<DetailEventAdapter.ViewHolder>(){
    private var datas = arrayListOf<DetailEventModel>()

    fun setData(mData: ArrayList<DetailEventModel>){
        datas = mData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_event_item, parent, false))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val home: TextView = view.tvHome
        private val title: TextView = view.tvTitle
        private val away: TextView = view.tvAway
        fun bind(data: DetailEventModel){
            home.text = data.home
            title.text = data.title
            away.text = data.away
        }
    }
}