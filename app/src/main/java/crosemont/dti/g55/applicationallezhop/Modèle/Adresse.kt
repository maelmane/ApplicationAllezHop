package crosemont.dti.g55.applicationallezhop.Modèle

import java.io.Serializable

data class Adresse(
    val numéroCivique : String,
    val rue : String,
    val ville : String,
    val codePostal : String,
    val pays : String
) : Serializable
