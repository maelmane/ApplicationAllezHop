package crosemont.dti.g55.applicationallezhop.Modèle

import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceDeDonnées

class ModèleTrajet(var sourceDeDonnées: SourceDeDonnées) {
    var _trajetsÀVenir = mutableListOf<Trajet>()

    fun obtenirTrajetÀVenir(indice : Int) : Trajet{
        return _trajetsÀVenir[indice]
    }

    fun chargerTrajetsÀVenir() : MutableList<Trajet>{
        _trajetsÀVenir = sourceDeDonnées.chargerTrajetsÀVenir()
        return _trajetsÀVenir
    }

    fun réserver(trajet: Trajet){
        sourceDeDonnées.créer(trajet)
    }

    val tailleTrajetsÀVenir: Int
        get() = _trajetsÀVenir.size
}