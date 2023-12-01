package crosemont.dti.g55.applicationallezhop.PageTrajet

import crosemont.dti.g55.applicationallezhop.Modèle.Trajet

interface IPrésentateurTrajet {

    fun effectuerRéservation(position: Int)

    val nbItems: Int

    fun getTrajetsVenirData(): List<Trajet>

    fun filtrerSelonDate(date: String): MutableList<Trajet>
}