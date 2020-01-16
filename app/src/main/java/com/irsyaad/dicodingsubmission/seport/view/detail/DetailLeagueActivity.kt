package com.irsyaad.dicodingsubmission.seport.view.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.adapter.ViewPagerAdapter
import com.irsyaad.dicodingsubmission.seport.model.SportModel
import com.irsyaad.dicodingsubmission.seport.model.response.ListDetailLeague
import com.irsyaad.dicodingsubmission.seport.view.main.MainActivity
import com.irsyaad.dicodingsubmission.seport.viewModel.ListViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailLeagueActivity : AppCompatActivity() {

    private lateinit var viewModel: ListViewModel
    private lateinit var data: SportModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        data = intent.getParcelableExtra(MainActivity().keyParcelable)!!

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        viewModel.getDetailLeague(data.id).observe(this, Observer { result ->
            result?.let { setLayout(it) } ?: run { viewModel.isError.value = true }
        })

        setSupportActionBar(toolbar)
        setViewPager()
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

    private fun setViewPager() {
        viewPager.adapter = ViewPagerAdapter(this, supportFragmentManager, "DetailLeague")
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setLayout(result: ListDetailLeague) {
        Glide.with(this)
            .load(result.strBadge)
            .centerCrop()
            .into(ivBadgeLeague)

        tvTitleLeague.text = result.strLeague
        tvDescription.text = data.description
    }

    fun getId(): Int {
        val id = data.id.toString()
        return id.toInt()
    }

}
