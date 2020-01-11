package com.irsyaad.dicodingsubmission.seport.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.adapter.ViewPagerAdapter
import com.irsyaad.dicodingsubmission.seport.model.response.ListDetailLeague
import com.irsyaad.dicodingsubmission.seport.model.SportModel
import com.irsyaad.dicodingsubmission.seport.view.main.MainActivity
import com.irsyaad.dicodingsubmission.seport.viewModel.ListViewModel
import com.irsyaad.dicodingsubmission.seport.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*


class DetailLeagueActivity : AppCompatActivity() {

    private lateinit var viewModel: ListViewModel
    private lateinit var data: SportModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        data = intent.getParcelableExtra(MainActivity().keyParcelable)

        viewModel = ViewModelProviders.of(this, ViewModelFactory().viewModelFactory{ ListViewModel(data.id)})[ListViewModel::class.java]

        viewModel.getDetailLeague().observe(this, Observer { result ->
            if(result != null){
                setLayout(result, data)
            }else{
                viewModel.isError.value = true
            }
        })

        setSupportActionBar(toolbar)
        setViewPager()

//        DetailUI(data).setContentView(this)
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

    private fun setViewPager(){
        viewPager.adapter = ViewPagerAdapter(this, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setLayout(result: ListDetailLeague, data: SportModel){
        Glide.with(this)
            .load(result.strBadge)
            .centerCrop()
            .into(ivBadgeLeague)

        tvTitleLeague.text = result.strLeague
        tvWebsite.text = result.strWebsite
    }

    fun getId(): Int {
        val id = data.id.toString()
        return id.toInt()
    }
}
