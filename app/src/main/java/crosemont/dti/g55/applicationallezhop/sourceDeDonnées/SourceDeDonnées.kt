package crosemont.dti.g55.applicationallezhop.sourceDeDonnées

import crosemont.dti.g55.applicationallezhop.Modèle.Trajet

class SourceDeDonnéesException( message: String) : Exception( message ) {}

interface SourceDeDonnées {
	fun getTrajetsVenirData(): MutableList<Trajet>
	fun getTrajetsAnciensData(): MutableList<Trajet>

	fun créer(unT: Trajet): Trajet

	fun toutCharger(): MutableList<Trajet>
	fun lire(uid: Long): Trajet

	fun chargerTrajetsÀVenir() : MutableList<Trajet>

	fun chargerAnciensTrajet() : MutableList<Trajet>

	fun supprimerTrajet(position: Int)

}
