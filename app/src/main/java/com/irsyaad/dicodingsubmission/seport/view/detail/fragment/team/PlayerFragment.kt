package com.irsyaad.dicodingsubmission.seport.view.detail.fragment.team


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.adapter.PlayerAdapter
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailLeagueViewModel
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailEventViewModel
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailTeamActivity
import kotlinx.android.synthetic.main.fragment_player.*

/**
 * A simple [Fragment] subclass.
 */
class PlayerFragment : Fragment() {
    private lateinit var viewModel: DetailLeagueViewModel
    private lateinit var playerAdapter: PlayerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as DetailTeamActivity
        val data = activity.getData()

        viewModel = ViewModelProvider(this).get(DetailLeagueViewModel::class.java)

        isLoading()

        viewModel.getAllPlayer(data.strTeam).observe(viewLifecycleOwner, Observer { result ->
            result?.let { playerAdapter.setData(it) } ?: run { viewModel.isError.value = true}
        })

        playerAdapter = PlayerAdapter(context!!)

        rvPlayer.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = playerAdapter
        }
    }

    private fun isLoading(){
        viewModel.showLoading.observe(viewLifecycleOwner, Observer {status ->
            if(status){
                loading.visibility = View.VISIBLE
                rvPlayer.visibility = View.GONE
            }else{
                rvPlayer.visibility = View.VISIBLE
                loading.visibility = View.GONE
            }
        })
    }


}
