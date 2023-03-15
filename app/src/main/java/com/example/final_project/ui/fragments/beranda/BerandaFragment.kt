package com.example.final_project.ui.fragments.beranda

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.adapters.*
import com.example.final_project.databinding.FragmentBerandaBinding
import com.example.final_project.models.*
import com.example.final_project.models.`object`.DetailTourismObjects
import com.example.final_project.models.`object`.MenuTourismObjects
import com.example.final_project.models.`object`.NearestEventObjects
import com.example.final_project.remote.preferences.Preferences
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
//import com.example.final_project.ui.activities.DetailTourismActivity
import dagger.hilt.android.AndroidEntryPoint

class BerandaFragment : Fragment() {
    private var _binding: FragmentBerandaBinding? = null
    private val binding get() = _binding

    //tourism menu
    private lateinit var menuTourismAdapter: MenuTourismAdapter
    private var menuTourism: ArrayList<MenuTourism> = arrayListOf()

    private lateinit var berandaViewModel: BerandaViewModel
    private var lokasi: String = ""

    //nearest event
//    private lateinit var nearestEventAdapter: NearestEventAdapter
    private var nearestEvent: ArrayList<NearestEvent> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBerandaBinding.inflate(inflater,container,false)
        return  binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManagerOne: RecyclerView.LayoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        val layoutManagerTwo: RecyclerView.LayoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        val repository = Repository()
        val berandaViewModelFactory = BerandaViewModelFactory(repository)
        val userID = Preferences.instance.userID

        berandaViewModel = ViewModelProvider(this, berandaViewModelFactory).get()
        getUserLocation(userID.toString())

        //rv tourism menu
        _binding!!.rvTourismMenu.layoutManager = layoutManagerOne
        menuTourism.addAll(MenuTourismObjects.menus_object)

        menuTourismAdapter = MenuTourismAdapter(menuTourism)
        _binding!!.rvTourismMenu.adapter = menuTourismAdapter

        //rv nearest event
        _binding!!.rvNearestEvent.layoutManager = layoutManagerTwo
//        nearestEventAdapter = NearestEventAdapter(nearestEvent)
//        _binding!!.rvNearestEvent.adapter = nearestEventAdapter

        //nearest tourism
        _binding!!.frameNearestTourism.setOnClickListener{
//            val intent = Intent(it.context, DetailTourismActivity::class.java)
//
////            intent.putExtra("tourism_detail", DetailTourismObjects.detail_tourism_objects.first())
//            startActivity(intent)
        }
    }

    fun getWeatherData(fetchedLoc: String) {
        val apiKey = "6b65118b7e78bb10c3697fa23e9dd76e"
        val lang = "id"

        berandaViewModel.getWeatherData(fetchedLoc, apiKey, lang)
        berandaViewModel._weatherLiveData.observe(viewLifecycleOwner) { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { weatherResponse ->
                        _binding?.weatherDesc?.text = weatherResponse.weather[0].description.capitalize() + ","

                        val fomattedTemp = String.format("%.2f", weatherResponse.main.temp - 273.15).toDouble()
                        _binding?.tempNum?.text = fomattedTemp.toString()
                    }
                }

                is Resource.Loading -> {

                }

                is Resource.Error -> {
                    Toast.makeText(activity, "Gagal mendapat data cuaca", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
    fun getUserLocation(userID: String) {
        berandaViewModel.getUserData(userID)

        berandaViewModel._userLiveData.observe(viewLifecycleOwner) {response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { profileResponse ->
                        if (profileResponse.data.location.isNullOrEmpty()) {
                            _binding?.lokasiTv?.text = "Lokasi belum diisi"
                            lokasi = ""
                        } else {
                            _binding?.lokasiTv?.text = profileResponse.data.location
                            lokasi = profileResponse.data.location
                        }

                        getWeatherData(lokasi)
                    }
                }

                is Resource.Loading -> {

                }

                is Resource.Error -> {
                    response.message.let { message ->
                        Toast.makeText(activity, "Gagal mendapat data user", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}