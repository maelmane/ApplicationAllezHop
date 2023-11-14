package crosemont.dti.g55.applicationallezhop.sourceDeDonnées

import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import kotlin.random.Random
import kotlinx.coroutines.delay

class SourceBidon : SourceDeDonnées {

	override fun getTrajetsVenirData(): List<Trajet> {
		return listOf(
			Trajet("04/01/2023", "6400 16e Avenue,...", "Iris"),
			Trajet("06/01/2023", "6400 16e Avenue,...", "Bobby"),
			Trajet("09/01/2023", "6400 16e Avenue,...", "Sacha"),
			Trajet("12/01/2023", "6400 16e Avenue,...", "Megan")
		)
	}

	override fun getTrajetsAnciensData(): List<Trajet> {

		return listOf(
			Trajet("03/01/2023", "6400 16e Avenue,...", "Megan"),
			Trajet("02/01/2023", "6400 16e Avenue,...", "Sacha"),
			Trajet("01/01/2023", "6400 16e Avenue,...", "Bobby")
		)
	}


}
