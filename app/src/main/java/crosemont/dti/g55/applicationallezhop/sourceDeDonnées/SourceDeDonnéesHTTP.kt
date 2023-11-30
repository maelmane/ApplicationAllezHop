package crosemont.dti.g55.applicationallezhop.sourceDeDonnées
import android.util.JsonWriter
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter
 class SourceDeDonnéesHTTP(var url_api : String ): SourceDeDonnées  {


     override fun getTrajetsVenirData(): MutableList<Trajet> {
         TODO("Not yet implemented")
     }

     override fun getTrajetsAnciensData(): MutableList<Trajet> {
         TODO("Not yet implemented")
     }

     override fun créer(unT: Trajet): Trajet {
         TODO("Not yet implemented")
     }

     override fun lire(uid: Long): Trajet {
         TODO("Not yet implemented")
     }

     override fun chargerTrajetsÀRéserver(): MutableList<Trajet> {
         TODO("Not yet implemented")
     }

     override fun supprimerTrajet(position: Int) {
         TODO("Not yet implemented")
     }

     @Throws(SourceDeDonnéesException::class)
     override suspend fun obtenirUrl(lien: String): String {
         try {
             val client = OkHttpClient()
             val output = ByteArrayOutputStream()
             val writer = JsonWriter(OutputStreamWriter(output))

             writer.beginObject()
             writer.name("lien").value(lien)
             writer.endObject()
             writer.close()

             val corps = output.toString().toRequestBody("application/json".toMediaTypeOrNull())

             val requête = Request.Builder().url(url_api).post(corps).build()
             val réponse = client.newCall(requête).execute()

             if (réponse.code == 200){
                 return réponse.body.toString()
             }else{
                 throw SourceDeDonnéesException("Code: ${réponse.code}")
             }
         }catch(e: IOException){
             throw SourceDeDonnéesException(e.message ?: "Erreur inconnue")
         }
     }
 }