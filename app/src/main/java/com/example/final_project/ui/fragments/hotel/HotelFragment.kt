package com.example.final_project.ui.fragments.hotel

import android.R
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.final_project.adapters.HotelFacilitiesSelectorAdapter
import com.example.final_project.databinding.FragmentHotelBinding
import com.example.final_project.remote.FinalProjectApplication
import com.example.final_project.ui.activities.chooseHotelFacilities.ChooseHotelFacilitiesActivity
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class HotelFragment : Fragment() {

    private var _binding: FragmentHotelBinding? = null
    private val binding get() = _binding
    private lateinit var autoCompleteTextView: AutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHotelBinding.inflate(inflater,container,false)

        val application = context?.applicationContext
        autoCompleteTextView = binding!!.etDestination

        try {
            // Read the contents of the JSON file into a string
            val json = application?.assets?.open("cities.json")?.bufferedReader().use { it?.readText() }

            // Parse the JSON string into a JSONArray
            val jsonObject = JSONObject(json!!)

            val jsonArray = jsonObject.getJSONArray("city")
            Log.d("JSON", jsonArray.toString())

            // Extract the values that you want to display in the AutoCompleteTextView
            val cityList = mutableListOf<String>()
            for (i in 0 until jsonArray.length()) {
                val city = jsonArray.getString(i)
                cityList.add(city)
            }

            // Create an adapter for the AutoCompleteTextView and set it to the view
            val adapter = ArrayAdapter(this.requireContext(), R.layout.simple_dropdown_item_1line, cityList)
            autoCompleteTextView.setAdapter(adapter)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return  binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnNext?.setOnClickListener {
            val intent = Intent(it.context, ChooseHotelFacilitiesActivity::class.java)
            intent.putExtra("HOTEL_DEST",binding?.etDestination?.text.toString())
            intent.putExtra("HOTEL_RATING",binding?.etRating?.text.toString().toDouble())
            startActivity(intent)
        }
    }
}