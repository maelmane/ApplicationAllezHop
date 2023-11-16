package crosemont.dti.g55.applicationallezhop.PageTrajet

import crosemont.dti.g55.applicationallezhop.Modèle.Trajet

interface IPrésentateurTrajet {
    fun rafraîchirAffichage()

    fun effectuerRéservation(position: Int)
    fun getConducteurString(position: Int): String?

    fun getHeureString(position: Int): String?

    val nbItems: Int

    fun getTrajetsVenirData(): List<Trajet>
}