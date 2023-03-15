package com.example.final_project.ui.fragments.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.final_project.R
import com.example.final_project.databinding.FragmentBerandaBinding
import com.example.final_project.databinding.FragmentProfilBinding
import com.example.final_project.remote.repository.Repository
import com.example.final_project.ui.fragments.wisata.WisataViewModel
import com.example.final_project.remote.preferences.Preferences
import com.example.final_project.util.Resource

class ProfilFragment : Fragment() {

    private var _binding: FragmentProfilBinding? = null
    private lateinit var profilViewModel: ProfilViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository = Repository()
        val profilViewModelFactory = ProfilViewModelFactory(repository)
        val userID = Preferences.instance.userID

        profilViewModel = ViewModelProvider(this, profilViewModelFactory).get()
        profilViewModel.getUserData(userID.toString())

        profilViewModel._userLiveData.observe(viewLifecycleOwner) {response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { profileResponse ->
                        _binding?.tvProfileName?.text = profileResponse.data.firstName + " " + profileResponse.data.lastName
                        _binding?.tvProfileEmail?.text = profileResponse.data.email

                        if (profileResponse.data.location.isNullOrEmpty()) {
                            _binding?.tvProfileLocation?.text = "Lokasi belum diisi"
                        } else {
                            _binding?.tvProfileLocation?.text = profileResponse.data.location
                        }
                    }
                }

                is Resource.Loading -> {

                }

                is Resource.Error -> {
                    response.message.let { message ->
                        Toast.makeText(activity, "gagal mendapat data user", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}