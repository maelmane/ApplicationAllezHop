package crosemont.dti.g55.applicationallezhop.PageTrajet

import crosemont.dti.g55.applicationallezhop.Modèle.ModèleTrajet
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.PageTrajet.vue_trajet
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon

class PrésentateurTrajet(var vue : vue_trajet) {
    val modèle=ModèleTrajet(SourceBidon())
    fun getTrajetsVenirData(): List<Trajet> {
        return modèle.sourceDeDonnées.getTrajetsVenirData()
    }
}