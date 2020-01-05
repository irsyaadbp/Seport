package com.irsyaad.dicodingsubmission.seport.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irsyaad.dicodingsubmission.seport.model.Sport
import com.irsyaad.dicodingsubmission.seport.view.main.ListUI
import org.jetbrains.anko.AnkoContext

class SportAdapter(private val context: Context, private val listener: (Sport) -> Unit) : RecyclerView.Adapter<SportAdapter.ViewHolder>() {

    private var datas: ArrayList<Sport> = arrayListOf()

    fun setData(mData: ArrayList<Sport>){
        datas = mData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position], listener, context)
        holder.itemView.setOnClickListener{listener(datas[position])}
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val title = itemView.findViewById<TextView>(ListUI.tvTitleLeague)
        private val image = itemView.findViewById<ImageView>(ListUI.ivBadge)
        private val container = itemView.findViewById<LinearLayout>(ListUI.vlContainer)

        fun bind(data: Sport, listener: (Sport) -> Unit, context: Context){
            title.text = data.league
            Glide.with(context)
                .load(data.badge)
                .centerCrop()
                .into(image)

            container.setOnClickListener { listener(data) }
        }
    }
}