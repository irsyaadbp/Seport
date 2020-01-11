package com.irsyaad.dicodingsubmission.seport.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.adapter.SportAdapter
import com.irsyaad.dicodingsubmission.seport.model.SportModel
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailLeagueActivity
import com.irsyaad.dicodingsubmission.seport.viewModel.ListViewModel
import com.irsyaad.dicodingsubmission.seport.viewModel.ViewModelFactory
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), AnkoLogger {

    private lateinit var sportAdapter: SportAdapter

    val keyParcelable = "SportModel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callViewModel()

        sportAdapter = SportAdapter(this) {
            //Toast Anko
            toast(it.league.toString())

            //Log Anko
            info("mencoba log.i di anko, anda klik ${it.league}")

            val item = SportModel(
                it.id,
                it.league,
                it.badge,
                it.description
            )

            //Intent Anko
            startActivity<DetailLeagueActivity>(
                keyParcelable to item
            )
        }

        MainUI(sportAdapter).setContentView(this)
    }

    private fun callViewModel() {
        val listViewModel = ViewModelProviders.of(this, ViewModelFactory().viewModelFactory{ ListViewModel()})[ListViewModel::class.java]

        listViewModel.getListLeague().observe(this, Observer {
            if(it != null){
                sportAdapter.setData(it)
            }
        })
    }

    override fun onBackPressed() {
        alert(resources.getString(R.string.exit_app)) {
            yesButton { super.onBackPressed() }
            noButton { }
        }.show()
    }
}
