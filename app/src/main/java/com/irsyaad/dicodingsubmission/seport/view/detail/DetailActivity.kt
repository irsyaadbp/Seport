package com.irsyaad.dicodingsubmission.seport.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.model.Sport
import org.jetbrains.anko.setContentView





class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val data: Sport? = intent.getParcelableExtra("Sport")

        title = data?.league ?: getString(R.string.app_name)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        DetailUI(data).setContentView(this)
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
}
