package crosemont.dti.g55.applicationallezhop.PageTrajet

import android.os.Bundle
import android.util.Log
import crosemont.dti.g55.applicationallezhop.Modèle.ModèleTrajet
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.PageTrajet.vue_trajet
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon

class PrésentateurTrajet(val vue : vue_trajet): IPrésentateurTrajet {
    var modèle = ModèleTrajet(SourceBidon.getInstance())
    override suspend fun getTrajetsVenirData(): MutableList<Trajet> {
        return modèle.chargerTrajetsÀVenir()
    }


    override suspend fun effectuerRéservation(position: Int) {
        var trajet = modèle.obtenirTrajetÀVenir(position)
//        modèle.réserver(trajet)
        val myBundle = Bundle().apply {
            putString("Conducteur", trajet.conducteur)
            putSerializable("Destination", trajet.destination)
            putString("HeureArrivé", trajet.heureArriver)
            putString("Date", trajet.date )
            putString("HeureDépart", trajet.heureDépart)
            putSerializable("Voiture", trajet.voiture)

        }

        vue.naviguerVerVueConfirmationRéservation(myBundle)
    }

    override val nbItems: Int
        get() = modèle.tailleTrajetsÀVenir
}