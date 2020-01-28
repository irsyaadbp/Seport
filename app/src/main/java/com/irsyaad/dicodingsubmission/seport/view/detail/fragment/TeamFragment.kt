package com.irsyaad.dicodingsubmission.seport.view.detail.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.adapter.AllTeamAdapter
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailLeagueViewModel
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailLeagueActivity
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailTeamActivity
import kotlinx.android.synthetic.main.fragment_team.*

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment() {
    private lateinit var viewModelDetail: DetailLeagueViewModel
    private lateinit var teamAdapter: AllTeamAdapter

    val keyTeam = "TEAM_DETAIL"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("result", "uye")
        val activity = activity as DetailLeagueActivity
        val id = activity.getId()

        viewModelDetail = ViewModelProvider(this).get(DetailLeagueViewModel::class.java)
        isLoading()
        viewModelDetail.getAllTeamLeague(id).observe(viewLifecycleOwner, Observer { result ->
            teamAdapter.setData(result)
            Log.d("result", ""+ result)
        })

        teamAdapter = AllTeamAdapter(context!!){
            val intent = Intent(context, DetailTeamActivity::class.java)
            intent.putExtra(keyTeam, it.idTeam.toInt())
            startActivity(intent)
        }
        rvTeam.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = teamAdapter
        }
    }

    private fun isLoading(){
        viewModelDetail.showLoading.observe(viewLifecycleOwner, Observer { status ->
            if(status){
                loading.visibility = View.VISIBLE
                rvTeam.visibility = View.GONE

            }else{
                rvTeam.visibility = View.VISIBLE
                loading.visibility = View.GONE
            }
        })
    }


}
