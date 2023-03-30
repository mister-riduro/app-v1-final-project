package com.example.final_project.ui.fragments.hotel

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.final_project.adapters.HotelFacilitiesSelectorAdapter
import com.example.final_project.databinding.FragmentHotelBinding
import com.example.final_project.ui.activities.chooseHotelFacilities.ChooseHotelFacilitiesActivity

class HotelFragment : Fragment() {

    private var _binding: FragmentHotelBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHotelBinding.inflate(inflater,container,false)
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