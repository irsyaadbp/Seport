package com.irsyaad.dicodingsubmission.seport.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.model.response.ListDetailTeam
import kotlinx.android.synthetic.main.layout_team_detail_item.view.*

class AllTeamAdapter(private val context: Context, private val listener: (ListDetailTeam) -> Unit): RecyclerView.Adapter<AllTeamAdapter.ViewHolder>() {
    private var datas = arrayListOf<ListDetailTeam>()

    fun setData(mData: ArrayList<ListDetailTeam>){
        datas = mData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_team_detail_item, parent, false))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position], context)
        holder.itemView.setOnClickListener{listener(datas[position])}
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val ivBadgeTeam = view.ivBadgeTeam
        private val tvClubName = view.tvClubName
        fun bind(data: ListDetailTeam, context: Context){
            tvClubName.text = data.strTeam

            Glide.with(context)
                .load(data.strTeamBadge)
                .centerCrop()
                .into(ivBadgeTeam)
        }
    }
}