package crosemont.dti.g55.applicationallezhop.PageTrajet

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
        vue.naviguerVerVueConfirmationRéservation()
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