package crosemont.dti.g55.applicationallezhop.PageTrajet

import android.os.Bundle
import android.util.Log
import crosemont.dti.g55.applicationallezhop.Modèle.ModèleTrajet
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.PageTrajet.vue_trajet
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon

class PrésentateurTrajet(val vue : vue_trajet): IPrésentateurTrajet {
    var modèle = ModèleTrajet(SourceBidon())
    override fun getTrajetsVenirData(): MutableList<Trajet> {
        return modèle.chargerTrajetsÀVenir()
    }

    override fun rafraîchirAffichage() {
        vue.rafraîchir()
    }

    override fun effectuerRéservation(position: Int) {
        var trajet = modèle.obtenirTrajetÀVenir(position)
        modèle.réserver(trajet)
        val myBundle = Bundle().apply {
            putString("Conducteur", modèle._trajetsÀVenir.get(position).conducteur)
            putString("AddresseEmbarcation", modèle._trajetsÀVenir.get(position).destination)
            putString("HeureArrivé", modèle._trajetsÀVenir.get(position).heureArriver)
            putString("Date", modèle._trajetsÀVenir.get(position).date )
            putString("HeureDépart",modèle._trajetsÀVenir.get(position).heureDépart)
            putSerializable("Voiture", modèle._trajetsÀVenir[position].voiture)

        }

        Log.d("vue_trajet", "Bundle : $myBundle") // Add this line

        vue.naviguerVerVueConfirmationRéservation(myBundle)
    }

    override fun getConducteurString(position: Int): String? {
        TODO("Not yet implemented")
    }

    override fun getHeureString(position: Int): String? {
        TODO("Not yet implemented")
    }

    override val nbItems: Int
        get() = modèle.tailleTrajetsÀVenir
}