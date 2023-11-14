package crosemont.dti.g55.applicationallezhop.sourceDeDonnées

import crosemont.dti.g55.applicationallezhop.Modèle.Trajet

interface SourceDeDonnées {
	fun getTrajetsVenirData(): List<Trajet>
	fun getTrajetsAnciensData(): List<Trajet>
}
