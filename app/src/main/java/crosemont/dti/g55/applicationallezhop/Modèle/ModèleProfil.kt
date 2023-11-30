package crosemont.dti.g55.applicationallezhop.Modèle

import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceDeDonnées

class ModèleProfil(var sourceDeDonnées: SourceDeDonnées) {
    private var _trajetsÀVenir = mutableListOf<Trajet>()
    var _anciensTrajets = mutableListOf<Trajet>()

    fun supprimerTrajet(indice : Int){
        _trajetsÀVenir.removeAt(indice)
    }

    fun obtenirTrajetÀVenir(indice : Int) : Trajet{
        return _trajetsÀVenir[indice]
    }

    fun obtenirAncienTrajet(indice : Int) : Trajet{
        return _anciensTrajets[indice]
    }

    fun chargerTrajetsÀVenir(): MutableList<Trajet>  {
        _trajetsÀVenir = sourceDeDonnées.getTrajetsVenirData()
        return _trajetsÀVenir
    }


    val tailleTrajetsÀVenir: Int
        get() = _trajetsÀVenir.size

    val tailleAnciensTrajets: Int
        get() = _anciensTrajets.size

    fun addTrajetVenir(trajet: Trajet) {
        _trajetsÀVenir.add(trajet)
    }

}