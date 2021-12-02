package com.m2i.naturecollection.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.m2i.naturecollection.*
import org.w3c.dom.Text

class PlantAdapter(
    val context: MainActivity,
    private val plantList: List<PlantModel>,
    private val layoutId : Int
) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    // Boite pour ranger tout les composants a controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        // Image de la plante
        val plantImage: ImageView = view.findViewById(R.id.image_item)
        // Nom de la plante
        val plantName: TextView? = view.findViewById(R.id.name_item)
        // Description de la plante
        val plantDescription: TextView? = view.findViewById(R.id.description_item)
        // etoile de like/unlike
        val startIcon = view.findViewById<ImageView>(R.id.star_icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recuperer les informations de la plante
        val currentPlant = plantList[position]

        // Recuperer le repository
        val repo = PlantRepository()

        //Utiliser la dépendance Glide pour recuperer l'image a partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentPlant.imageUrl)).into(holder.plantImage)

        // Mettre a jour le nom de la plante
        holder.plantName?.text = currentPlant.name

        // Mettre a jour la description de la plante
        holder.plantDescription?.text = currentPlant.description

        // Verifier si la plante a été likée
        if(currentPlant.liked){
            holder.startIcon.setImageResource(R.drawable.ic_like)
        } else {
            holder.startIcon.setImageResource(R.drawable.ic_unlike)
        }

        // Rajouter une interaction sur l'etoile pour pouvoir like / unlike
        holder.startIcon.setOnClickListener{
            //Inverser si le bouton et like ou non
            currentPlant.liked = !currentPlant.liked
            //Mettre a jour l'objet plant
            repo.updatePlant(currentPlant)
        }

        // Interaction lors du clic sur une plante
        holder.itemView.setOnClickListener{
            //Afficher la popup
            PlantPopup(this, currentPlant).show()
        }

    }

    override fun getItemCount(): Int = plantList.size

}