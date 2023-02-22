package com.example.final_project.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.adapters.TourismAreaAdapter
import com.example.final_project.databinding.FragmentWisataBinding
import com.example.final_project.models.Province
import com.example.final_project.models.`object`.TourismAreaObjects

class WisataFragment : Fragment() {
    private var _binding:FragmentWisataBinding? = null
    private val binding get() = _binding

    private lateinit var tourismAreaAdapter: TourismAreaAdapter
    private var areaTourism: ArrayList<Province> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWisataBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.actionBar?.isHideOnContentScrollEnabled = false
        activity?.actionBar?.title = "Wisata"

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        _binding!!.rvTourismArea.layoutManager = layoutManager

//        areaTourism.addAll(TourismAreaObjects.tourism_area)

        tourismAreaAdapter = TourismAreaAdapter(areaTourism)
        _binding!!.rvTourismArea.adapter = tourismAreaAdapter
    }
}