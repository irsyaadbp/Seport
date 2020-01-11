package com.irsyaad.dicodingsubmission.seport.view.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.adapter.EventAdapter
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailEventActivity
import com.irsyaad.dicodingsubmission.seport.viewModel.ListViewModel
import com.irsyaad.dicodingsubmission.seport.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private lateinit var viewModel: ListViewModel
    private lateinit var searchAdapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar)

        search()
        viewModel = ViewModelProviders.of(this, ViewModelFactory().viewModelFactory{ ListViewModel() })[ListViewModel::class.java]
        isLoading()
        viewModel.getSearchEvent().observe(this, Observer { result ->

            if(result != null){
                searchAdapter.setData(result)

                if(result.isEmpty()) {
                    Toast.makeText(this, getString(R.string.search_not_found), Toast.LENGTH_SHORT).show()
                    tvNotFound.visibility = View.VISIBLE
                    rvSearch.visibility = View.GONE
                    loading.visibility = View.GONE
                }
            }
        })

        searchAdapter = EventAdapter(this){
            val intent = Intent(this, DetailEventActivity::class.java)
            intent.putExtra(DetailEventActivity().keyDetailEvent, it)
            startActivity(intent)
        }
        rvSearch.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
    }

    private fun search(){
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.onActionViewExpanded()
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.setDataSearch(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.setDataSearch(query)
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

    private fun isLoading(){
        viewModel.showLoading.observe(this, Observer {status ->
            if(status){
                loading.visibility = View.VISIBLE
                rvSearch.visibility = View.GONE
                tvNotFound.visibility = View.GONE
            }else{
                rvSearch.visibility = View.VISIBLE
                loading.visibility = View.GONE
                tvNotFound.visibility = View.GONE
            }
        })
    }
}
