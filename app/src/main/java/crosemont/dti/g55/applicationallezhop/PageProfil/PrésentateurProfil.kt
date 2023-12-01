package crosemont.dti.g55.applicationallezhop.PageProfil

import android.os.Bundle
import android.util.Log
import crosemont.dti.g55.applicationallezhop.Modèle.ModèleProfil
import crosemont.dti.g55.applicationallezhop.Modèle.ModèleTrajet
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.PageTrajet.TrajetAdapter
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon

class PrésentateurProfil(var vue: vue_profil): IPrésentateurProfil {

    var modèle= ModèleProfil(SourceBidon.getInstance())
    private var _adapter: ProfilAdapter = ProfilAdapter(emptyList())
    override suspend fun getTrajetsVenirData(): List<Trajet> {
        return modèle.chargerTrajetsÀVenir()
    }

    override suspend fun getTrajetsAnciensData(): List<Trajet> {
        return  modèle.chargerTrajetsAnciens()
    }

    override suspend fun addReservedTrajet(trajet: Trajet) {
        modèle.sourceDeDonnées.créer(trajet)
        vue.rafraîchirAffichage()

    }


    override val nbItems: Int
        get() = modèle.tailleTrajetsÀVenir

}