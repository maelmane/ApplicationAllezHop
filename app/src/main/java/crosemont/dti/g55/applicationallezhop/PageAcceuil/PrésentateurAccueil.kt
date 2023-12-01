package crosemont.dti.g55.applicationallezhop.PageAcceuil

import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import crosemont.dti.g55.applicationallezhop.Modèle.Adresse
import crosemont.dti.g55.applicationallezhop.Modèle.ModèleTrajet
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon

class PrésentateurAccueil(var vue: vue_accueil) {
    var modèle = ModèleTrajet(SourceBidon.getInstance())

    fun getLatitudeLongitudeAdresse(uneAdresse: String) : LatLng {
        val coder = Geocoder(vue.requireContext())
        lateinit var adresses : List<Address>
        adresses = coder.getFromLocationName(uneAdresse, 1)!!
        var latitude = adresses.get(0).latitude
        var longitude = adresses.get(0).longitude
        return LatLng(latitude, longitude)
    }

    suspend fun getAdresseDestination(): String{
        return modèle.chargerTrajetsÀVenir().get(0).destination.toString()
    }

}
