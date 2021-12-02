package com.m2i.naturecollection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.m2i.naturecollection.MainActivity
import com.m2i.naturecollection.PlantRepository.Singleton.plantList
import com.m2i.naturecollection.R
import com.m2i.naturecollection.adapter.PlantAdapter

class CollectionFragment(
    private val context: MainActivity
) : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_collection, container, false)

        // Recuperer le recyclerView
        val collectionRecyclerView = view.findViewById<RecyclerView>(R.id.collection_recycler_list)
        collectionRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_vertical_plant)
        collectionRecyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }
}