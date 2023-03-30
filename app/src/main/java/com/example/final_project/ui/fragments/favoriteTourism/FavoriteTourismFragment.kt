package com.example.final_project.ui.fragments.favoriteTourism

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import com.example.final_project.adapters.FavoriteTourismAdapter
import com.example.final_project.databinding.FragmentFavoriteTourismBinding
import com.example.final_project.remote.repository.Repository
import com.example.final_project.ui.fragments.profile.ProfilViewModel
import com.example.final_project.util.Resource

class FavoriteTourismFragment : Fragment() {
    private var _binding: FragmentFavoriteTourismBinding? = null
    private val binding get() = _binding

    private lateinit var favoriteTourismAdapter: FavoriteTourismAdapter
    private lateinit var favTourismViewModel: FavoriteTourismViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteTourismBinding.inflate(inflater,container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository()
        val favoriteTourismViewModelFactory = FavoriteTourismViewModelFactory(repository)

        favTourismViewModel = ViewModelProvider(this, favoriteTourismViewModelFactory).get(FavoriteTourismViewModel::class.java)
        val userID = favTourismViewModel.getUserID()

        favTourismViewModel.getFavoriteTourism(userID)
        setupRecyclerView()
        favTourismViewModel._tourismLiveData.observe(viewLifecycleOwner) { response ->
            when(response) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    response.data.let {
                        favoriteTourismAdapter.differ.submitList(it?.data!![0].tourisms)
                    }
                }
            }
        }
    }

    fun setupRecyclerView() {
        favoriteTourismAdapter = FavoriteTourismAdapter()
        binding?.rvFavoriteTourism.apply {
            this?.layoutManager = GridLayoutManager(this?.context, 2)
            this?.adapter = favoriteTourismAdapter
        }
    }
}