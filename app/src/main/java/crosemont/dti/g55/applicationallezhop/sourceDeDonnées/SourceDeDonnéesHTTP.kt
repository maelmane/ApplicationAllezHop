package crosemont.dti.g55.applicationallezhop.sourceDeDonnées
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
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

     override fun toutCharger(): MutableList<Trajet> {
         TODO("Not yet implemented")
     }

     override fun lire(uid: Long): Trajet {
         TODO("Not yet implemented")
     }

     override fun chargerTrajetsÀRéserver(): MutableList<Trajet> {
         TODO("Not yet implemented")
     }

     override fun chargerAnciensTrajet(): MutableList<Trajet> {
         TODO("Not yet implemented")
     }

     override fun supprimerTrajet(position: Int) {
         TODO("Not yet implemented")
     }
 }