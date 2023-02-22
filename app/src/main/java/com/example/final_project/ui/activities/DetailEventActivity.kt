package com.example.final_project.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.final_project.R
import com.example.final_project.databinding.ActivityEventDetailBinding

class DetailEventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventDetailBinding

    companion object {
        const val EXTRA_BG = "extra_bg"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_DATE = "extra_date"
        const val EXTRA_LOCATION = "extra_location"
        const val EXTRA_DESC = "extra_desc"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvEventDetailName.text = intent.getStringExtra(EXTRA_NAME).toString()
        binding.tvEventDetailDate.text = intent.getStringExtra(EXTRA_DATE).toString()
        binding.tvEventDetailType.text = intent.getStringExtra(EXTRA_TYPE).toString()
        binding.tvEventDetailLocation.text = intent.getStringExtra(EXTRA_LOCATION).toString()
        binding.tvAboutEvent.text = intent.getStringExtra(EXTRA_DESC).toString()


        supportActionBar?.setIcon(R.drawable.icon_arrow_left)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Acara Terdekat"

        val bundle: Bundle = intent.extras!!
        val resId: Int = bundle.getInt(EXTRA_BG)
        binding.ivEvent.setImageResource(resId)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}