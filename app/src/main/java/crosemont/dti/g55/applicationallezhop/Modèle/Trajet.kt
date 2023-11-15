package crosemont.dti.g55.applicationallezhop.Modèle

import java.time.LocalTime

data class Trajet(
    val date: String,
    val destination: String,
    val conducteur: String,
    val heureArriver: String,
    val heureDépart: String,
    val voiture: Voiture
)