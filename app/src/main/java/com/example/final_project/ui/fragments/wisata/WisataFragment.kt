package com.example.final_project.ui.fragments.wisata

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.MainActivity
import com.example.final_project.adapters.TourismAreaAdapter
import com.example.final_project.databinding.FragmentWisataBinding
import com.example.final_project.models.Province
import com.example.final_project.models.`object`.TourismAreaObjects
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource

class WisataFragment : Fragment() {
    private var _binding:FragmentWisataBinding? = null
    private val binding get() = _binding

    private lateinit var tourismAreaAdapter: TourismAreaAdapter
    private lateinit var wisataViewModel: WisataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWisataBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository = Repository()
        val wisataViewModelFactory = WisataViewModelFactory(repository)

        wisataViewModel = ViewModelProvider(this, wisataViewModelFactory).get(wisataViewModel::class.java)
        wisataViewModel.getAllProvinces()

        setupRecyclerView()

        wisataViewModel._provinceLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        tourismAreaAdapter.differ.submitList(it.provinces)
                    }
                }

                is Resource.Loading -> {

                }

                is Resource.Error -> {
                    response.message.let { message ->
                        Log.d("ERROR PROVINCE", "Error occured : $message")
                    }
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)

        activity?.actionBar?.isHideOnContentScrollEnabled = false
        activity?.actionBar?.title = "Wisata"
    }

    private fun setupRecyclerView() {
        tourismAreaAdapter = TourismAreaAdapter()
        _binding!!.rvTourismArea.apply {
            adapter = tourismAreaAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }
}