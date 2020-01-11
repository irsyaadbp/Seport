package com.irsyaad.dicodingsubmission.seport.view.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.adapter.DetailEventAdapter
import com.irsyaad.dicodingsubmission.seport.model.EventModel
import com.irsyaad.dicodingsubmission.seport.model.response.ListDetailTeam
import com.irsyaad.dicodingsubmission.seport.view.detail.fragment.NextEventFragment
import com.irsyaad.dicodingsubmission.seport.viewModel.ListViewModel
import com.irsyaad.dicodingsubmission.seport.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.toolbar
import kotlinx.android.synthetic.main.activity_detail_event.*

class DetailEventActivity : AppCompatActivity() {

    private lateinit var viewModel: ListViewModel
    private lateinit var data: EventModel
    private lateinit var detailEventAdapter: DetailEventAdapter

    val keyDetailEvent = "DETAIL_EVENT"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event)

        data = intent.getParcelableExtra(keyDetailEvent)

        viewModel = ViewModelProviders.of(this, ViewModelFactory().viewModelFactory{ ListViewModel(data.id!!.toInt())})[ListViewModel::class.java]

        isLoading()
        setLayout()

        viewModel.getDetailEventLeague().observe(this, Observer { result ->
            if(result != null){
                detailEventAdapter.setData(result)
            }else{
                viewModel.isError.value = true
            }
        })

        setSupportActionBar(toolbar)

        detailEventAdapter = DetailEventAdapter(this)
        rvDetailEvent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = detailEventAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setLayout(){
        tvDetailHomeName.text = data.homeTeam
        tvDetailAwayName.text = data.awayTeam
        tvDateTime.text = data.date
        tvDetailHomeScore.text = data.homeTeamScore
        tvDetailAwayScore.text = data.awayTeamScore

        viewModel.getHomeTeam(data.idHomeTeam!!.toInt()).observe(this, Observer {result ->
            if(result != null){
                Glide.with(this)
                    .load(result.strTeamBadge)
                    .centerCrop()
                    .into(ivDetailHomeLogo)
            }else{
                viewModel.isError.value = true
            }
        })

        viewModel.getAwayTeam(data.idAwayTeam!!.toInt()).observe(this, Observer {result ->
            if(result != null){
                Glide.with(this)
                    .load(result.strTeamBadge)
                    .centerCrop()
                    .into(ivDetailAwayLogo)
            }else{
                viewModel.isError.value = true
            }
        })
    }

    private fun isLoading(){
        viewModel.showLoading.observe(this, Observer {status ->
            if(status){
                loading.visibility = View.VISIBLE
                rvDetailEvent.visibility = View.GONE
            }else{
                rvDetailEvent.visibility = View.VISIBLE
                loading.visibility = View.GONE
            }
        })
    }
}
