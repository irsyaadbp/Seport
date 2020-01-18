package com.irsyaad.dicodingsubmission.seport.view.favorite.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.adapter.EventAdapter
import com.irsyaad.dicodingsubmission.seport.model.EventModel
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailEventActivity
import com.irsyaad.dicodingsubmission.seport.view.favorite.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_next_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class NextFavoriteFragment : Fragment() {
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

//        isLoading()

        viewModel.getNextEventFavorite.observe(viewLifecycleOwner, Observer { result ->
            result?.let {
                if(result.isNotEmpty()){
                    val eventData = result.map {
                        EventModel(
                            it.idData.toString(),
                            it.idHomeTeam.toString(),
                            it.idAwayTeam.toString(),
                            it.name,
                            it.date,
                            it.time,
                            it.homeTeam,
                            it.awayTeam,
                            it.homeTeamScore,
                            it.awayTeamScore,
                            it.thumbnails)
                    }
                    Log.d("data next", "" + eventData)
                    eventAdapter.setData(eventData)
                    txtNotFound.visibility = View.GONE
                    rvNextEvent.visibility = View.VISIBLE
                }else{
                    txtNotFound.visibility = View.VISIBLE
                    rvNextEvent.visibility = View.GONE
                }
            } ?: run {
                txtNotFound.visibility = View.VISIBLE
                rvNextEvent.visibility = View.GONE
            }

        })

        eventAdapter = EventAdapter(context!!){
            val intent = Intent(context, DetailEventActivity::class.java)
            intent.putExtra(DetailEventActivity().keyDetailEvent, it)
            intent.putExtra(DetailEventActivity().keyTypeEvent, "next")
            startActivity(intent)
        }
        rvNextEvent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = eventAdapter
        }

    }
}
