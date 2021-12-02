package com.m2i.naturecollection

class PlantModel (
    val id: String = "plant0",
    val name: String = "Tulipe",
    val description: String = "Petite description",
    val imageUrl: String = "http://graven.yt/plante.jpg",
    val grow: String = "Moyenne",
    val water: String = "Faible",
    var liked: Boolean = false
)