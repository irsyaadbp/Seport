package com.irsyaad.dicodingsubmission.seport.view.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.adapter.AllTeamAdapter
import com.irsyaad.dicodingsubmission.seport.adapter.EventAdapter
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailEventActivity
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailTeamActivity
import com.irsyaad.dicodingsubmission.seport.view.detail.fragment.TeamFragment
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private var viewModel: SearchViewModel? = null
    private var searchAdapter: EventAdapter? = null
    private var teamAdapter: AllTeamAdapter? = null

    private var search: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar)

        search = intent.getStringExtra(SearchOptionsActivity().keySearch)

        search()
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        isLoading()
        search?.let {
            when (it) {
                "events" -> {
                    viewModel?.let { vm ->
                        vm.getSearchEvent().observe(this, Observer { result ->
                            result?.let {
                                searchAdapter?.setData(result)
                                if (result.isEmpty()) {
                                    Toast.makeText(
                                        this,
                                        getString(R.string.search_not_found),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    tvNotFound.visibility = View.VISIBLE
                                    rvSearch.visibility = View.GONE
                                    loading.visibility = View.GONE
                                }
                            } ?: run { vm.isError.value = true }

                        })
                    }

                    searchAdapter = EventAdapter(this) { event ->
                        val intent = Intent(this, DetailEventActivity::class.java)
                        intent.putExtra(DetailEventActivity().keyDetailEvent, event)
                        startActivity(intent)
                    }

                    rvSearch.apply {
                        layoutManager = LinearLayoutManager(this@SearchActivity)
                        adapter = searchAdapter
                    }
                }
                else -> {
                    viewModel?.let { vm ->
                        vm.getSearchTeam.observe(this, Observer { result ->
                            result?.let {
                                teamAdapter?.setData(result)
                                if (result.isEmpty()) {
                                    Toast.makeText(
                                        this,
                                        getString(R.string.search_not_found),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    tvNotFound.visibility = View.VISIBLE
                                    rvSearch.visibility = View.GONE
                                    loading.visibility = View.GONE
                                }
                            } ?: run { vm.isError.value = true }

                        })
                    }

                    teamAdapter = AllTeamAdapter(this) { team ->
                        val intent = Intent(this, DetailTeamActivity::class.java)
                        intent.putExtra(TeamFragment().keyTeam, team.idTeam.toInt())
                        startActivity(intent)
                    }

                    rvSearch.apply {
                        layoutManager = GridLayoutManager(this@SearchActivity, 2)
                        adapter = teamAdapter
                    }
                }
            }
        }


    }

    private fun search() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.onActionViewExpanded()
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                search?.let {
                    if (it == "events") viewModel?.setDataSearch(query)
                    else viewModel?.setDataSearchTeam(query)
                }
                return true
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun isLoading() {
        viewModel?.showLoading?.observe(this, Observer { status ->
            if (status) {
                loading.visibility = View.VISIBLE
                rvSearch.visibility = View.GONE
                tvNotFound.visibility = View.GONE
            } else {
                rvSearch.visibility = View.VISIBLE
                loading.visibility = View.GONE
                tvNotFound.visibility = View.GONE
            }
        })
    }
}
