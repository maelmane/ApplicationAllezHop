package crosemont.dti.g55.applicationallezhop.PageProfil
import crosemont.dti.g55.applicationallezhop.Modèle.ModèleProfil
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceDeDonnées

import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon

class PrésentateurProfil(var vue: vue_profil): IPrésentateurProfil {

    val modèle= ModèleProfil(SourceBidon())
    
    override fun getTrajetsVenirData(): List<Trajet> {
        return modèle.sourceDeDonnées.getTrajetsVenirData()
    }

    override fun getTrajetsAnciensData(): List<Trajet> {
        return  modèle.sourceDeDonnées.getTrajetsAnciensData()
    }

    override fun requêteSupprimerTrajetProfil(position: Int) {
        TODO("Not yet implemented")
    }

    override fun rafraîchirAffichage() {
        TODO("Not yet implemented")
    }

    override fun getItemString(position: Int): String? {
        TODO("Not yet implemented")
    }

    override fun getConducteurString(position: Int): String? {
        TODO("Not yet implemented")
    }

    override fun getAdresseString(position: Int): String? {
        TODO("Not yet implemented")
    }

    override fun getDateString(position: Int): String? {
        TODO("Not yet implemented")
    }

    override val nbItems: Int
        get() = modèle.tailleTrajetsÀVenir

}