package crosemont.dti.g55.applicationallezhop.Modèle

import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceDeDonnées
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceDeDonnéesHTTP

class ModèleProfil(var sourceDeDonnées: SourceDeDonnées) {
    private var _trajetsÀVenir = mutableListOf<Trajet>()
    private var sourceHttp = SourceDeDonnéesHTTP("https://0898b9b0-2661-4bde-95e6-4ca06e12bef0.mock.pstmn.io")
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

    suspend fun chargerTrajetsÀVenir(): MutableList<Trajet>  {
        _trajetsÀVenir = sourceDeDonnées.getTrajetsVenirData()
        return _trajetsÀVenir
    }

    suspend fun chargerTrajetsAnciens(): MutableList<Trajet>  {
        _anciensTrajets = sourceHttp.getTrajetsAnciensData("https://0898b9b0-2661-4bde-95e6-4ca06e12bef0.mock.pstmn.io/trajet")
        return _anciensTrajets
    }


    val tailleTrajetsÀVenir: Int
        get() = _trajetsÀVenir.size

    val tailleAnciensTrajets: Int
        get() = _anciensTrajets.size

    fun addTrajetVenir(trajet: Trajet) {
        _trajetsÀVenir.add(trajet)
    }

}