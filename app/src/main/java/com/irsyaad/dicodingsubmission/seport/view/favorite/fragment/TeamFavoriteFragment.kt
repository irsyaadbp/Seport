package com.irsyaad.dicodingsubmission.seport.view.favorite.fragment


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
import com.irsyaad.dicodingsubmission.seport.model.response.ListDetailTeam
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailLeagueViewModel
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailTeamActivity
import com.irsyaad.dicodingsubmission.seport.view.detail.fragment.TeamFragment
import com.irsyaad.dicodingsubmission.seport.view.favorite.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_team_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class TeamFavoriteFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var teamAdapter: AllTeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        viewModel.getTeamFavorite.observe(viewLifecycleOwner, Observer { result ->
            result?.let {
                if(result.isNotEmpty()){
                    val teamData = result.map {
                        ListDetailTeam("", "", it.idTeam.toString(),
                            "", "", "", "",
                            "", "", "", "",
                            "", "", "", "",
                            "", "", "", "",
                            "", "", "", "",
                            "", "", "", "", "",
                            "", "", "", "", "",
                            "", "", "", "",
                            it.clubName.toString(), it.badge.toString(), "", "",
                            "", "", "", "",
                            "", "", "", "", "")
                    }

                    teamAdapter.setData(teamData as ArrayList<ListDetailTeam>)
                    txtNotFound.visibility = View.GONE
                    rvTeamFav.visibility = View.VISIBLE
                }else{
                    txtNotFound.visibility = View.VISIBLE
                    rvTeamFav.visibility = View.GONE
                }
            } ?: run {
                txtNotFound.visibility = View.VISIBLE
                rvTeamFav.visibility = View.GONE
            }
        })


        teamAdapter = AllTeamAdapter(context!!){
            val intent = Intent(context, DetailTeamActivity::class.java)
            intent.putExtra(TeamFragment().keyTeam, it.idTeam.toInt())
            startActivity(intent)
        }
        rvTeamFav.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = teamAdapter
        }
    }
}
