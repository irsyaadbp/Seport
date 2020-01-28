package com.irsyaad.dicodingsubmission.seport.view.detail.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.adapter.StandingAdapter
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailLeagueViewModel
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailLeagueActivity
import kotlinx.android.synthetic.main.fragment_standing.*

/**
 * A simple [Fragment] subclass.
 */
class StandingFragment : Fragment() {
    private lateinit var viewModelDetail: DetailLeagueViewModel
    private lateinit var standingAdapter: StandingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as DetailLeagueActivity
        val id = activity.getId()

        viewModelDetail = ViewModelProvider(this).get(DetailLeagueViewModel::class.java)
        isLoading()
        viewModelDetail.getStandingTable(id).observe(viewLifecycleOwner, Observer { result ->
            standingAdapter.setData(result)
        })

        standingAdapter = StandingAdapter(context!!)
        rvStanding.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = standingAdapter
        }
    }

    private fun isLoading(){
        viewModelDetail.showLoading.observe(viewLifecycleOwner, Observer { status ->
            if(status){
                loading.visibility = View.VISIBLE
                rvStanding.visibility = View.GONE

            }else{
                rvStanding.visibility = View.VISIBLE
                loading.visibility = View.GONE
            }
        })
    }

}
