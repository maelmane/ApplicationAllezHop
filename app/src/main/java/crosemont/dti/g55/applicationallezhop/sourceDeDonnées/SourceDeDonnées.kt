package crosemont.dti.g55.applicationallezhop.sourceDeDonnées

import crosemont.dti.g55.applicationallezhop.Domaine.Entité.Donnée
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet

class SourceDeDonnéesException( message: String) : Exception( message ) {}

interface SourceDeDonnées {
	fun getTrajetsVenirData(): MutableList<Trajet>
	fun getTrajetsAnciensData(): MutableList<Trajet>

	fun créer(unT: Trajet): Trajet

	fun lire(uid: Long): Trajet

	fun chargerTrajetsÀRéserver() : MutableList<Trajet>

	fun supprimerTrajet(position: Int)

	suspend fun obtenirUrl(lien: String) : String

}
