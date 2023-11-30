	package crosemont.dti.g55.applicationallezhop.sourceDeDonnées

	import crosemont.dti.g55.applicationallezhop.Domaine.Entité.Donnée
	import crosemont.dti.g55.applicationallezhop.Modèle.Adresse
	import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
	import crosemont.dti.g55.applicationallezhop.Modèle.Voiture
	import kotlin.random.Random
	import kotlinx.coroutines.delay

	class SourceBidon() : SourceDeDonnées {

		companion object {
			private var instance: SourceBidon? = null

			fun getInstance(): SourceBidon {
				if (instance == null) {
					instance = SourceBidon()
				}
				return instance!!
			}
		}

		private val trajetsVenir = mutableListOf<Trajet>()

		override fun getTrajetsVenirData(): MutableList<Trajet> {
			return trajetsVenir.toMutableList()
		}


		override fun getTrajetsAnciensData(): MutableList<Trajet> {

			return mutableListOf(
				Trajet("03/01/2023", Adresse( numéroCivique = "6400", rue = "16e Avenue", ville = "Montréal", codePostal = "H1X 2S9", pays = "Canada"), "Iris", "8:00", "7:00", voiture = Voiture(null)),
				Trajet("02/01/2023", Adresse( numéroCivique = "6400", rue = "16e Avenue", ville = "Montréal", codePostal = "H1X 2S9", pays = "Canada"), "Sacha", "8:00", "7:00", voiture = Voiture(null)),
				Trajet("01/01/2023", Adresse( numéroCivique = "6400", rue = "16e Avenue", ville = "Montréal", codePostal = "H1X 2S9", pays = "Canada"), "Bobby", "7:45", "6:48", voiture = Voiture(null))
			)

		}

		override fun créer(unT: Trajet): Trajet {
			trajetsVenir.add(unT)
			return unT
		}

		override fun chargerTrajetsÀRéserver() : MutableList<Trajet> {
			return mutableListOf(
				Trajet("04/01/2023", Adresse( numéroCivique = "6400", rue = "16e Avenue", ville = "Montréal", codePostal = "H1X 2S9", pays = "Canada"), "Iris", "8:00", "7:00",  Voiture(null)),
				Trajet("06/01/2023", Adresse( numéroCivique = "6400", rue = "16e Avenue", ville = "Montréal", codePostal = "H1X 2S9", pays = "Canada"), "Bobby", "8:00", "7:25", Voiture(null)),
				Trajet("09/01/2023", Adresse( numéroCivique = "6400", rue = "16e Avenue", ville = "Montréal", codePostal = "H1X 2S9", pays = "Canada"), "Sacha", "7:30", "6:59", Voiture(null)),
				Trajet("12/12/2023", Adresse( numéroCivique = "6400", rue = "16e Avenue", ville = "Montréal", codePostal = "H1X 2S9", pays = "Canada"), "Megan", "7:07", "6:32", Voiture(null))
			)
		}


		override fun supprimerTrajet(position: Int) {
			TODO("Not yet implemented")
		}


		override suspend fun obtenirUrl(lien: String): String {
			return "https://AllezHop.com"
		}

		override fun lire(uid: Long): Trajet {
			TODO("Not yet implemented")
		}


	}
