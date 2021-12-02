package com.m2i.naturecollection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.m2i.naturecollection.MainActivity
import com.m2i.naturecollection.PlantModel
import com.m2i.naturecollection.PlantRepository.Singleton.plantList
import com.m2i.naturecollection.R
import com.m2i.naturecollection.adapter.PlantAdapter
import com.m2i.naturecollection.adapter.PlantItemDecoration

class HomeFragment(
    private val context: MainActivity
) : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater?.inflate(R.layout.fragment_home, container, false)

        // Recuperer le recyclerView
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_horizontal_plant)

        // Recuperer le second RecyclerView
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_vertical_plant)
        verticalRecyclerView.addItemDecoration(PlantItemDecoration())

        return view
    }
    // //https://cdn.pixabay.com/photo/2016/05/31/23/21/peony-1428053_960_720.jpg
}