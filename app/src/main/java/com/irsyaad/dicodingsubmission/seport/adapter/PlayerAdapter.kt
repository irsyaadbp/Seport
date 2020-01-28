package com.irsyaad.dicodingsubmission.seport.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.model.response.ListPlayer
import kotlinx.android.synthetic.main.layout_player_item.view.*

class PlayerAdapter(val context: Context) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivPlayer = view.ivPlayer
        private val tvPlayerName = view.tvPlayerName
        private val tvPosition = view.tvPosition

        fun bind(data: ListPlayer, context: Context){
            tvPlayerName.text = data.strPlayer
            tvPosition.text = data.strPosition
            Glide.with(context).load(data.strCutout).centerCrop().into(ivPlayer)
        }
    }

    private lateinit var datas: ArrayList<ListPlayer>

    fun setData(mData: ArrayList<ListPlayer>){
        Log.d("mData", "mData")
        datas = mData
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.layout_player_item,
                parent,
                false
            ))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position], context)
    }

}