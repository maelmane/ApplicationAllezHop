package crosemont.dti.g55.applicationallezhop.PageTrajet

import crosemont.dti.g55.applicationallezhop.Modèle.Trajet

interface IPrésentateurTrajet {

    suspend fun effectuerRéservation(position: Int)

    val nbItems: Int


    suspend fun getTrajetsVenirData(): List<Trajet>


    fun filtrerSelonDate(date: String)

    fun filtrerSelonHeure(heure: String)

    fun filtrerSelonAdresse(adresse: String)

}