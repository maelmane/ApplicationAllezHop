package crosemont.dti.g55.applicationallezhop.sourceDeDonnées

import android.util.JsonReader
import android.util.JsonToken
import android.util.Log
import crosemont.dti.g55.applicationallezhop.Domaine.Entité.Donnée
import crosemont.dti.g55.applicationallezhop.Domaine.Entité.Serveur
import crosemont.dti.g55.applicationallezhop.Modèle.Adresse
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.Modèle.Voiture
import java.io.StringReader
import kotlin.math.log

class DécodeurJson {
	companion object {

		/**
		 * Crée un objet Donnée à partir de sa représentation JSON
		 *
		 * @params json Une chaîne de caractères en format JSON représentant une Donnée
		 * @return La Donnée créée
		 */
		fun décoderJsonVersDonnée(json: String): Donnée {
			var trajet = mutableListOf<Trajet>()

			val reader = JsonReader(StringReader(json))

			if (reader.peek() == JsonToken.BEGIN_ARRAY){
				reader.beginArray()
					while (reader.hasNext()) {
						reader.beginObject()
						lateinit var date: String
						lateinit var destination: Adresse
						lateinit var conducteur: String
						lateinit var heureArriver: String
						lateinit var heureDépart: String
						lateinit var voiture: Voiture

						while (reader.hasNext()) {
							val clé = reader.nextName()
							when (clé) {
								"date" -> date = reader.nextString()
								"destination" -> destination = décoderJsonVersAdresse(reader)
								"conducteur" -> conducteur = reader.nextString()
								"heureArriver" -> heureArriver = reader.nextString()
								"heureDépart" -> heureDépart = reader.nextString()
								"voiture" -> voiture = décoderJsonVersVoiture(reader)
								else -> reader.skipValue()
							}
						}
						reader.endObject()

						val unTrajet = Trajet(date, destination, conducteur, heureArriver, heureDépart, voiture)
						trajet.add(unTrajet)
					}
				reader.endArray()
			}
			else{
				reader.beginObject()
				lateinit var date: String
				lateinit var destination: Adresse
				lateinit var conducteur: String
				lateinit var heureArriver: String
				lateinit var heureDépart: String
				lateinit var voiture: Voiture

				while (reader.hasNext()) {
					val clé = reader.nextName()
					when (clé) {
						"date" -> date = reader.nextString()
						"destination" -> {
							Log.d("Test", "Test avant la destination")
							destination = décoderJsonVersAdresse(reader)
							Log.d("Test", "Test après la destination: $destination")
						}

						"conducteur" -> conducteur = reader.nextString()
						"heureArriver" -> heureArriver = reader.nextString()
						"heureDépart" -> heureDépart = reader.nextString()
						"voiture" -> {
							Log.d("Test", "Test avant la voiture")
							voiture = décoderJsonVersVoiture(reader)
							Log.d("Test", "Test après la voiture")
						}

						else -> reader.skipValue()
					}
				}
				Log.d("Test", "Test avant le deuxieme end object dans le while")
				reader.endObject()

				val unTrajet = Trajet(date, destination, conducteur, heureArriver, heureDépart, voiture)
				trajet.add(unTrajet)
			}

			return Donnée(trajet)
		}

		fun décoderJsonVersAdresse(reader: JsonReader): Adresse {
			lateinit var numéroCivique: String
			lateinit var rue: String
			lateinit var ville: String
			lateinit var codePostal: String
			lateinit var pays: String

			reader.beginObject()
			while (reader.hasNext()) {
				val clé = reader.nextName()
				when (clé) {
					"numéroCivique" -> numéroCivique = reader.nextString()
					"rue" -> rue = reader.nextString()
					"ville" -> ville = reader.nextString()
					"codePostal" -> codePostal = reader.nextString()
					"pays" -> pays = reader.nextString()
					else -> reader.skipValue()
				}
			}
			reader.endObject()

			return Adresse(numéroCivique, rue, ville, codePostal, pays)
		}

		fun décoderJsonVersVoiture(reader: JsonReader): Voiture {
			var plaqueImatriculation: String? = null

			reader.beginObject()
			while (reader.hasNext()) {
				val clé = reader.nextName()
				when (clé) {
					"plaqueImatriculation" -> plaqueImatriculation = reader.nextString()
					else -> reader.skipValue()
				}
			}
			reader.endObject()

			return Voiture(plaqueImatriculation)
		}

		private fun décoderJsonVersServeur( reader: JsonReader ): Serveur {
			lateinit var nom : String
			lateinit var url : String
			
			reader.beginObject()
			while (reader.hasNext() ) {
				var clé = reader.nextName()
				when( clé ) {
					"nom" ->{
						nom = reader.nextString()
					}
					"url" ->{
						url = reader.nextString()
					}
				}
			}
			reader.endObject()
			return Serveur( nom, url )
		}
	}
}
