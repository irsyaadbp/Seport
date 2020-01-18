package com.irsyaad.dicodingsubmission.seport.view.detail.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.adapter.EventAdapter
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailEventActivity
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailEventViewModel
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailLeagueActivity
import kotlinx.android.synthetic.main.fragment_past_event.*

/**
 * A simple [Fragment] subclass.
 */
class PastEventFragment : Fragment() {
    private lateinit var viewModelDetail: DetailEventViewModel
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_past_event, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as DetailLeagueActivity
        val id = activity.getId()

        viewModelDetail = ViewModelProvider(this).get(DetailEventViewModel::class.java)

        isLoading()

        viewModelDetail.getPastEvent(id).observe(viewLifecycleOwner, Observer { result ->
            result?.let { eventAdapter.setData(it) } ?: run { viewModelDetail.isError.value = true}
        })

        eventAdapter = EventAdapter(context!!){
            val intent = Intent(context, DetailEventActivity::class.java)
            intent.putExtra(DetailEventActivity().keyDetailEvent, it)
            intent.putExtra(DetailEventActivity().keyTypeEvent, "past")
            startActivity(intent)
        }
        rvPastEvent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = eventAdapter
        }

    }

    private fun isLoading(){
        viewModelDetail.showLoading.observe(viewLifecycleOwner, Observer { status ->
            if(status){
                loading.visibility = View.VISIBLE
                rvPastEvent.visibility = View.GONE
            }else{
                rvPastEvent.visibility = View.VISIBLE
                loading.visibility = View.GONE
            }
        })
    }
}
