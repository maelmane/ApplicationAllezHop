package crosemont.dti.g55.applicationallezhop.PageProfil
import crosemont.dti.g55.applicationallezhop.Modèle.ModèleProfil
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceDeDonnées

import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon

class PrésentateurProfil(var vue: vue_profil) {

    val modèle= ModèleProfil(SourceBidon())
    
    fun getTrajetsVenirData(): List<Trajet> {
        return modèle.sourceDeDonnées.getTrajetsVenirData()
    }

    fun getTrajetsAnciensData(): List<Trajet> {
        return  modèle.sourceDeDonnées.getTrajetsAnciensData()
    }


}