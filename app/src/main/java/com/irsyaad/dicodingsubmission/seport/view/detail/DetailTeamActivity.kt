package com.irsyaad.dicodingsubmission.seport.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.adapter.ViewPagerAdapter
import com.irsyaad.dicodingsubmission.seport.model.FavoriteTeamModel
import com.irsyaad.dicodingsubmission.seport.model.response.ListDetailTeam
import com.irsyaad.dicodingsubmission.seport.view.detail.fragment.TeamFragment
import com.irsyaad.dicodingsubmission.seport.view.favorite.FavoriteViewModel
import kotlinx.android.synthetic.main.activity_detail_team.*
import kotlinx.android.synthetic.main.activity_detail_team.ivBadgeLeague
import kotlinx.android.synthetic.main.activity_detail_team.tabLayout
import kotlinx.android.synthetic.main.activity_detail_team.toolbar
import kotlinx.android.synthetic.main.activity_detail_team.tvDescription
import kotlinx.android.synthetic.main.activity_detail_team.viewPager

class DetailTeamActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailLeagueViewModel
    private lateinit var data: ListDetailTeam
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        val id = intent.getIntExtra(TeamFragment().keyTeam, 0)

        viewModel = ViewModelProvider(this).get(DetailLeagueViewModel::class.java)
        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        viewModel.getDetailTeam(id).observe(this, Observer { result ->
            result?.let {
                setLayout(it)
                data = it
                favoriteViewModel.checkFavoriteTeam(it.idTeam.toInt())
                setViewPager()
            } ?: run { viewModel.isError.value = true }
        })

        setSupportActionBar(toolbar)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.om_favorite -> {
                favoriteViewModel.isFavorite.value = !favoriteViewModel.isFavorite.value!!

                if (favoriteViewModel.isFavorite.value!!) {
                    favoriteViewModel.insertFavoriteTeam(
                        FavoriteTeamModel(data.idTeam.toInt(), data.strTeamBadge, data.strTeam)
                    )
                    Toast.makeText(this, getString(R.string.favorite), Toast.LENGTH_SHORT).show()
                } else {
                    favoriteViewModel.deleteFavoriteTeam(data.idTeam.toInt())
                    Toast.makeText(this, getString(R.string.remove_favorite), Toast.LENGTH_SHORT).show()
                }

                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
            this.menu = menu
            val inflater = menuInflater
            inflater.inflate(R.menu.favorite_menu, menu)
            isFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    private fun setViewPager() {
        viewPager.adapter = ViewPagerAdapter(this, supportFragmentManager, "Team")
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setLayout(result: ListDetailTeam) {
        Glide.with(this)
            .load(result.strTeamBadge)
            .centerCrop()
            .into(ivBadgeLeague)

        tvClubName.text = result.strTeam
        tvDescription.text = result.strCountry
    }

    fun getData(): ListDetailTeam{
        return data
    }

    private fun isFavorite(){
        favoriteViewModel.isFavorite.observe(this, Observer {status ->
            Log.d("status", ""+status)
            when {
                status -> menu.getItem(0).icon = getDrawable(R.drawable.ic_favorite_pink_24dp)
                else -> menu.getItem(0).icon = getDrawable(R.drawable.ic_favorite_border_white_24dp)
            }
        })
    }
}
