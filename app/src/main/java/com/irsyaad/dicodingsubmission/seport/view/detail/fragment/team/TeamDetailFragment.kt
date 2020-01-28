package com.irsyaad.dicodingsubmission.seport.view.detail.fragment.team


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.model.response.ListDetailTeam
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailLeagueViewModel
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailTeamActivity
import kotlinx.android.synthetic.main.fragment_team_detail.*

/**
 * A simple [Fragment] subclass.
 */
class TeamDetailFragment : Fragment() {
    private lateinit var viewModel: DetailLeagueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as DetailTeamActivity
        val data = activity.getData()

        setLayout(data)
    }

    fun setLayout(data: ListDetailTeam){
        tvDescription.text = data.strDescriptionEN
        tvLeague.text = data.strLeague
        tvStadium.text = data.strStadium
        tvLocation.text = data.strStadiumLocation
        tvWebsite.text = data.strWebsite
    }
}
