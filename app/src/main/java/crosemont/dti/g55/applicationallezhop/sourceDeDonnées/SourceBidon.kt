package crosemont.dti.g55.applicationallezhop.sourceDeDonnées

import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.Modèle.Voiture
import kotlin.random.Random
import kotlinx.coroutines.delay

class SourceBidon(/*override val trajetsVenirData: Any*/) : SourceDeDonnées {

	override fun getTrajetsVenirData(): MutableList<Trajet> {
		return mutableListOf(
			Trajet("04/01/2023", "6400 16e Avenue,...", "Iris", "8:00", "7:00", voiture = Voiture(null)),
			Trajet("06/01/2023", "6400 16e Avenue,...", "Bobby", "8:00", "7:25", voiture = Voiture(null)),
			Trajet("09/01/2023", "6400 16e Avenue,...", "Sacha", "7:30", "6:59", voiture = Voiture(null)),
			Trajet("12/01/2023", "6400 16e Avenue,...", "Megan", "7:07", "6:32", voiture = Voiture(null))
		)
	}

	override fun getTrajetsAnciensData(): MutableList<Trajet> {

		return mutableListOf(
			Trajet("03/01/2023", "6400 16e Avenue,...", "Megan", "8:00", "7:00", voiture = Voiture(null)),
			Trajet("02/01/2023", "6400 16e Avenue,...", "Sacha", "8:00", "7:00", voiture = Voiture(null)),
			Trajet("01/01/2023", "6400 16e Avenue,...", "Bobby", "7:45", "6:48", voiture = Voiture(null))
		)
	}

	override fun créer(unT: Trajet): Trajet {
		TODO("Not yet implemented")
	}

	override fun toutCharger(): MutableList<Trajet> {
		TODO("Not yet implemented")
	}

	override fun chargerTrajetsÀVenir() : MutableList<Trajet> {
		return mutableListOf(
			Trajet("04/01/2023", "6400 16e Avenue,...", "Iris", "8:00", "7:00",  Voiture(null)),
			Trajet("06/01/2023", "6400 16e Avenue,...", "Bobby", "8:00", "7:25", Voiture(null)),
			Trajet("09/01/2023", "6400 16e Avenue,...", "Sacha", "7:30", "6:59", Voiture(null)),
			Trajet("12/01/2023", "6400 16e Avenue,...", "Megan", "7:07", "6:32", Voiture(null))
		)
	}

	override fun chargerAnciensTrajet() : MutableList<Trajet> {
		return mutableListOf(
			Trajet("03/01/2023", "6400 16e Avenue,...", "Megan", "8:00", "7:00", voiture = Voiture(null)),
			Trajet("02/01/2023", "6400 16e Avenue,...", "Sacha", "8:00", "7:00", voiture = Voiture(null)),
			Trajet("01/01/2023", "6400 16e Avenue,...", "Bobby", "7:45", "6:48", voiture = Voiture(null))
		)
	}

	override fun supprimerTrajet(position: Int) {
		TODO("Not yet implemented")
	}

	override fun lire(uid: Long): Trajet {
		TODO("Not yet implemented")
	}


}
