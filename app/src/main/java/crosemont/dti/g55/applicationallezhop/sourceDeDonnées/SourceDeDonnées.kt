package crosemont.dti.g55.applicationallezhop.sourceDeDonnées

import crosemont.dti.g55.applicationallezhop.Domaine.Entité.Donnée
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet



interface SourceDeDonnées {
	suspend fun getTrajetsVenirData(): MutableList<Trajet>
	suspend fun getTrajetsAnciensData(): MutableList<Trajet>

	fun créer(unT: Trajet): Trajet

	fun lire(uid: Long): Trajet

	fun chargerTrajetsÀRéserver() : MutableList<Trajet>

	fun supprimerTrajet(position: Int)

	suspend fun obtenirUrl(lien: String) : String

}
class SourceDeDonnéesException( message: String) : Exception( message ) {}