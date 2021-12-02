package com.m2i.naturecollection

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.m2i.naturecollection.PlantRepository.Singleton.databaseRef
import com.m2i.naturecollection.PlantRepository.Singleton.plantList

class PlantRepository {

    object Singleton {
        // se connecter a la reference "plants"
        val databaseRef = FirebaseDatabase.getInstance().getReference("plants")

        // creer une liste qui va contenir les plantes
        val plantList = arrayListOf<PlantModel>()
    }

    fun updateData(callback: () -> Unit){
        //Absorber les données depuis la databaseRef pour les donner a la liste de plantes (plantList)
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //Retirer les anciennes
                plantList.clear()
                // Recolter la liste
                for (ds in snapshot.children) {
                    //construire un objet plante
                    val plant = ds.getValue(PlantModel::class.java)

                    // Verifier que la plante n'est pas "null"
                    if(plant != null) {
                        // ajouter la plante a notre liste
                        plantList.add(plant)
                    }
                }
                // Actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }

    // Mettre a jour un objet plante en bdd
    fun updatePlant(plant: PlantModel) = databaseRef.child(plant.id).setValue(plant)
    fun deletePlant(plant: PlantModel) = databaseRef.child(plant.id).removeValue()



}