package crosemont.dti.g55.applicationallezhop.PageProfil

import android.os.Bundle
import android.util.Log
import crosemont.dti.g55.applicationallezhop.Modèle.ModèleProfil
import crosemont.dti.g55.applicationallezhop.Modèle.ModèleTrajet
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.PageTrajet.TrajetAdapter
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon

class PrésentateurProfil(var vue: vue_profil): IPrésentateurProfil {

    var modèle= ModèleProfil(SourceBidon())
    var modèleTrajet= ModèleTrajet(SourceBidon())
    private var _adapter: ProfilAdapter = ProfilAdapter(emptyList())
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
        Log.d("DEBUG", "rafraîchirAffichage called")
        _adapter?.notifyDataSetChanged()
    }

    override fun getItemString(position: Int): String? {
        TODO("Not yet implemented")
    }

    override fun getConducteurString(position: Int): String? {
        TODO("Not yet implemented")
    }
    override fun addReservedTrajet(trajet: Trajet) {
        Log.d("DEBUG", "Adding new trajet: $trajet")
        modèle.sourceDeDonnées.créer(trajet)
        rafraîchirAffichage()

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