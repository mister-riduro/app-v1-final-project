package com.example.final_project.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.adapters.*
import com.example.final_project.databinding.FragmentBerandaBinding
import com.example.final_project.models.*
import com.example.final_project.ui.activities.ActivityEventDetail
import com.example.final_project.ui.activities.TourismDetailActivity


class BerandaFragment : Fragment() {
    private var _binding: FragmentBerandaBinding? = null
    private val binding get() = _binding

    //tourism menu
    private lateinit var menuTourismAdapter: MenuTourismAdapter
    private var menuTourism: ArrayList<MenuTourism> = arrayListOf()

    //nearest event
    private lateinit var nearestEventAdapter: NearestEventAdapter
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

        //rv tourism menu
        _binding!!.rvTourismMenu.layoutManager = layoutManagerOne
        menuTourism.addAll(MenuTourismObjects.menus_object)

        menuTourismAdapter = MenuTourismAdapter(menuTourism)
        _binding!!.rvTourismMenu.adapter = menuTourismAdapter

        //rv nearest event
        _binding!!.rvNearestEvent.layoutManager = layoutManagerTwo

        nearestEvent.addAll(NearestEventObjects.nearest_events)

        nearestEventAdapter = NearestEventAdapter(nearestEvent)
        _binding!!.rvNearestEvent.adapter = nearestEventAdapter

        //nearest tourism
        _binding!!.frameNearestTourism.setOnClickListener{
            val intent = Intent(it.context, TourismDetailActivity::class.java)

            intent.putExtra("tourism_detail", DetailTourismObjects.detail_tourism_objects.first())
            startActivity(intent)
        }
    }
}