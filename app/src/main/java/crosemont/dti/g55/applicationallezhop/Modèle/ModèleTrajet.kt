package crosemont.dti.g55.applicationallezhop.Modèle

import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceDeDonnées

class ModèleTrajet(var sourceDeDonnées: SourceDeDonnées) {
    var _trajetsÀVenir = mutableListOf<Trajet>()

    fun obtenirTrajetÀVenir(indice : Int) : Trajet{
        return _trajetsÀVenir[indice]
    }

    fun chargerTrajetsÀVenir() {
        _trajetsÀVenir = sourceDeDonnées.chargerTrajetsÀVenir()
    }

    fun réserver(trajet: Trajet){
        TODO()
    }

    val tailleTrajetsÀVenir: Int
        get() = _trajetsÀVenir.size
}