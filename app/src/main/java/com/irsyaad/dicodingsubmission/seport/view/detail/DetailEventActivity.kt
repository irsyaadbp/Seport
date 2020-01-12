package com.irsyaad.dicodingsubmission.seport.view.detail

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.adapter.DetailEventAdapter
import com.irsyaad.dicodingsubmission.seport.model.EventModel
import com.irsyaad.dicodingsubmission.seport.model.FavoriteModel
import com.irsyaad.dicodingsubmission.seport.viewModel.ListViewModel
import kotlinx.android.synthetic.main.activity_detail.toolbar
import kotlinx.android.synthetic.main.activity_detail_event.*

class DetailEventActivity : AppCompatActivity() {

    private lateinit var viewModel: ListViewModel
    private lateinit var data: EventModel
    private lateinit var type: String
    private lateinit var detailEventAdapter: DetailEventAdapter
    private var favorite: Boolean = false

    private lateinit var menu: Menu

    val keyDetailEvent = "DETAIL_EVENT"
    val keyTypeEvent = "TYPE_EVENT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event)

        data = intent.getParcelableExtra(keyDetailEvent)!!
        type = intent.getStringExtra(keyTypeEvent)!!

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        isLoading()
        setLayout()

        viewModel.getDetailEventLeague(data.id!!.toInt()).observe(this, Observer { result ->
            result?.let {
                detailEventAdapter.setData(it)
                viewModel.checkFavorite(data.id!!.toInt(), type)
            } ?: run { viewModel.isError.value = true}
        })

        setSupportActionBar(toolbar)

        detailEventAdapter = DetailEventAdapter(this)
        rvDetailEvent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = detailEventAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.om_favorite -> {
                viewModel.isFavorite.value = !viewModel.isFavorite.value!!
                if (viewModel.isFavorite.value!!) {
                    viewModel.insertFavorite(
                        FavoriteModel(
                            data.id!!.toInt(),
                            data.idHomeTeam!!.toInt(),
                            data.homeTeam!!,
                            data.homeTeamScore,
                            data.idAwayTeam!!.toInt(),
                            data.awayTeam!!,
                            data.awayTeamScore,
                            data.name,
                            data.date,
                            data.time,
                            data.thumbnails,
                            type
                        )
                    )
                    Toast.makeText(this, getString(R.string.favorite), Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.deleteFavorite(data.id!!.toInt(), type)
                    Toast.makeText(this, getString(R.string.remove_favorite), Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        this.menu = menu
        val inflater = menuInflater
        inflater.inflate(R.menu.favorite_menu, menu)
        isFavorite()
        return super.onCreateOptionsMenu(menu)
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

    private fun isFavorite(){
        viewModel.isFavorite.observe(this, Observer {status ->
            favorite = status
            Log.d("status", ""+status)
            when {
                status -> menu.getItem(0).icon = getDrawable(R.drawable.ic_favorite_pink_24dp)
                else -> menu.getItem(0).icon = getDrawable(R.drawable.ic_favorite_border_black_24dp)
            }
        })
    }
}
