package crosemont.dti.g55.applicationallezhop.sourceDeDonnées

import crosemont.dti.g55.applicationallezhop.Modèle.Trajet

interface SourceDeDonnées {
	fun getTrajetsVenirData(): List<Trajet>
	fun getTrajetsAnciensData(): List<Trajet>

	fun créer(unT: Trajet): Trajet

	fun toutCharger(): MutableList<Trajet>
	fun lire(uid: Long): Trajet

	fun chargerTrajetsÀVenir() : MutableList<Trajet>

	fun chargerAnciensTrajet() : MutableList<Trajet>
}
