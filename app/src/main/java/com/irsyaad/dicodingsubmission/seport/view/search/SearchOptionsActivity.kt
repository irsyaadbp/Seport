package com.irsyaad.dicodingsubmission.seport.view.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.irsyaad.dicodingsubmission.seport.R
import kotlinx.android.synthetic.main.activity_search_options.*

class SearchOptionsActivity : AppCompatActivity() {
    val keySearch = "TYPE_SEARCH"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_options)

        setSupportActionBar(toolbar)
        title = "Search"

        tvMatch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra(keySearch, "events")
            startActivity(intent)
        }

        tvTeam.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra(keySearch, "team")
            startActivity(intent)
        }
    }
}
