package crosemont.dti.g55.applicationallezhop.sourceDeDonnées

import android.util.JsonReader
import crosemont.dti.g55.applicationallezhop.Domaine.Entité.Donnée
import crosemont.dti.g55.applicationallezhop.Domaine.Entité.Serveur
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import java.io.StringReader

class DécodeurJson {
	companion object {

		/**
		 * Crée un objet Donnée à partir de sa représentation JSON
		 *
		 * @params json Une chaîne de caractères en format JSON représentant une Donnée
		 * @return La Donnée créée
		 */
		fun décoderJsonVersDonnée( json : String ): Donnée {
			var trajet = emptyArray<Trajet>()
			val reader = JsonReader(StringReader( json ) )
			lateinit var description : String
			lateinit var serveur : Serveur

			reader.beginObject()
			while (reader.hasNext()) {
				val clé = reader.nextName()
				
				when( clé ) {
					"données" -> {
						reader.beginArray()
						while(reader.hasNext()){
							//trajet += reader.nextInt()
						}
						reader.endArray()
					}
					"description" -> { description = reader.nextString() }
					"serveur" -> {
						serveur = décoderJsonVersServeur( reader )
					}
				}
			}
			reader.endObject()

			return Donnée( trajet, description, serveur )
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
