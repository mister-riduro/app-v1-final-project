package com.example.final_project.ui.fragments.favoriteHotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.final_project.adapters.FavoriteHotelAdapter
import com.example.final_project.databinding.FragmentFavoriteHotelBinding
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource


class FavoriteHotelFragment : Fragment() {
    private var _binding: FragmentFavoriteHotelBinding? = null
    private val binding get() = _binding

    private lateinit var favoriteHotelAdapter: FavoriteHotelAdapter
    private lateinit var favoriteHotelViewModel: FavoriteHotelViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteHotelBinding.inflate(inflater,container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository()
        val favoriteHotelViewModelFactory = FavoriteHotelViewModelFactory(repository)

//        favoriteHotelViewModel = ViewModelProvider(this, favoriteHotelViewModelFactory).get(FavoriteHotelViewModel::class.java)
//        val userID = favoriteHotelViewModel.getUserID()
//
//        favoriteHotelViewModel.getFavoriteHotel(userID)
//        setupRecyclerView()
//        favoriteHotelViewModel._hotelLiveData.observe(viewLifecycleOwner) { response ->
//            when(response) {
//                is Resource.Error -> {
//
//                }
//                is Resource.Loading -> {
//
//                }
//                is Resource.Success -> {
//                    response.data.let {
//                        favoriteHotelAdapter.differ.submitList(it?.data?.get(0)?.hotels)
//                    }
//                }
//            }
//        }
    }

    private fun setupRecyclerView() {
        favoriteHotelAdapter = FavoriteHotelAdapter()
        binding?.rvFavoriteHotel.apply {
            this?.layoutManager = GridLayoutManager(this?.context, 2)
            this?.adapter = favoriteHotelAdapter
        }
    }


}