package crosemont.dti.g55.applicationallezhop.PageTrajet

import android.os.Bundle
import android.util.Log
import crosemont.dti.g55.applicationallezhop.Modèle.ModèleTrajet
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.PageTrajet.vue_trajet
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PrésentateurTrajet(val vue : vue_trajet): IPrésentateurTrajet {
    var modèle = ModèleTrajet(SourceBidon.getInstance())
    override suspend fun getTrajetsVenirData(): MutableList<Trajet> {
        return modèle.chargerTrajetsÀVenir()
    }

    override fun filtrerSelonDate(date: String) {
        CoroutineScope(Dispatchers.IO).launch {
            modèle.filtrerTrajetsSelonDate(date)

            CoroutineScope(Dispatchers.Main).launch {   vue.rafraichir()}
        }
    }

    override fun filtrerSelonHeure(heure: String) {
        CoroutineScope(Dispatchers.IO).launch {
            modèle.filtrerTrajetsSelonHeure(heure)

            CoroutineScope(Dispatchers.Main).launch {   vue.rafraichir()}
        }
    }

    override fun filtrerSelonAdresse(adresse: String) {
        CoroutineScope(Dispatchers.IO).launch {
            modèle.filtrerTrajetsSelonAdresse(adresse)

            CoroutineScope(Dispatchers.Main).launch {   vue.rafraichir()}
        }
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
        CoroutineScope(Dispatchers.Main).launch {
        vue.naviguerVerVueConfirmationRéservation(myBundle)
        }
    }


    override val nbItems: Int
        get() = modèle.tailleTrajetsÀVenir
}