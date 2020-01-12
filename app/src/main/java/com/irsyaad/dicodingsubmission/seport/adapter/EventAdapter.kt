package com.irsyaad.dicodingsubmission.seport.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.model.EventModel
import com.irsyaad.dicodingsubmission.seport.model.response.DetailTeamLeague
import com.irsyaad.dicodingsubmission.seport.model.service.network.ApiRepository
import com.irsyaad.dicodingsubmission.seport.model.service.network.ApiService
import kotlinx.android.synthetic.main.layout_team_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventAdapter(private val context: Context, private val listener: (EventModel) -> Unit) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    private var datas: List<EventModel> = listOf()
    private val service = ApiRepository.getData()

    fun setData(mData: List<EventModel>){
        Log.d("mData", "mData")
        datas = mData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.layout_team_item,
                parent,
                false
            ))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position], context, service)
        holder.itemView.setOnClickListener{listener(datas[position])}
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val tvHomeName: TextView = view.tvDetailHomeName
        private val tvAwayName: TextView = view.tvDetailAwayName
        private val tvHomesScore: TextView = view.tvDetailHomeScore
        private val tvAwayScore: TextView = view.tvDetailAwayScore
        private val ivHomeLogo: ImageView = view.ivDetailHomeLogo
        private val ivAwayLogo: ImageView = view.containerBadgeTeam


        fun bind(data: EventModel, context: Context, service: ApiService){
            tvHomeName.text = data.homeTeam
            tvAwayName.text = data.awayTeam
            tvHomesScore.text = data.homeTeamScore ?: "-"
            tvAwayScore.text = data.awayTeamScore ?: "-"

            ivHomeLogo.setImageResource(android.R.color.transparent)
            ivAwayLogo.setImageResource(android.R.color.transparent)

            service.detailTeam(data.idHomeTeam!!.toInt()).enqueue(object : Callback<DetailTeamLeague>{
                override fun onFailure(call: Call<DetailTeamLeague>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<DetailTeamLeague>, response: Response<DetailTeamLeague>) {
                    val image = response.body()?.teams?.get(0)?.strTeamBadge
                    Glide.with(context)
                        .load(image)
                        .centerCrop()
                        .into(ivHomeLogo)
                }

            })


            service.detailTeam(data.idAwayTeam!!.toInt()).enqueue(object : Callback<DetailTeamLeague>{
                override fun onFailure(call: Call<DetailTeamLeague>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<DetailTeamLeague>, response: Response<DetailTeamLeague>) {
                    val image = response.body()?.teams?.get(0)?.strTeamBadge
                    Glide.with(context)
                        .load(image)
                        .centerCrop()
                        .into(ivAwayLogo)
                }

            })


//            container.setOnClickListener { listener(data) }
        }
    }
}