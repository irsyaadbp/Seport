package com.irsyaad.dicodingsubmission.seport.view.detail.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.adapter.EventAdapter
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailEventActivity
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailLeagueActivity
import com.irsyaad.dicodingsubmission.seport.viewModel.ListViewModel
import com.irsyaad.dicodingsubmission.seport.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_next_event.*
/**
 * A simple [Fragment] subclass.
 */
class NextEventFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_event, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as DetailLeagueActivity
        val id = activity.getId()

        viewModel = ViewModelProviders.of(this, ViewModelFactory().viewModelFactory{ ListViewModel(id) })[ListViewModel::class.java]
        isLoading()
        viewModel.getNextEvent().observe(this, Observer { result ->
            if(result != null){
                eventAdapter.setData(result)
            }else{
                viewModel.isError.value = true
            }
        })

        eventAdapter = EventAdapter(context!!){
            val intent = Intent(context, DetailEventActivity::class.java)
            intent.putExtra(DetailEventActivity().keyDetailEvent, it)
            startActivity(intent)
        }
        rvNextEvent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = eventAdapter
        }
    }

    private fun isLoading(){
        viewModel.showLoading.observe(this, Observer {status ->
            if(status){
                loading.visibility = View.VISIBLE
                rvNextEvent.visibility = View.GONE
            }else{
                rvNextEvent.visibility = View.VISIBLE
                loading.visibility = View.GONE
            }
        })
    }
}
