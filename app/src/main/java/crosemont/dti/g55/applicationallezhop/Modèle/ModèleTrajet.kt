package crosemont.dti.g55.applicationallezhop.Modèle

import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceDeDonnées

class ModèleTrajet(var sourceDeDonnées: SourceDeDonnées) {
    var _trajetsÀVenir = mutableListOf<Trajet>()

    fun obtenirTrajetÀVenir(indice : Int) : Trajet{
        return _trajetsÀVenir[indice]
    }

    fun chargerTrajetsÀVenir() : MutableList<Trajet>{
        _trajetsÀVenir = sourceDeDonnées.chargerTrajetsÀRéserver()
        return _trajetsÀVenir
    }

    fun réserver(trajet: Trajet){
        sourceDeDonnées.créer(trajet)
    }

    fun filtrerTrajetsSelonDate(date: String): MutableList<Trajet>{
        var listeTrajetsFiltrés = mutableListOf<Trajet>()
        for (trajet in _trajetsÀVenir){
            if (trajet.date == date){
                listeTrajetsFiltrés.add(trajet)
            }
        }
        _trajetsÀVenir = listeTrajetsFiltrés
        return listeTrajetsFiltrés
    }

    val tailleTrajetsÀVenir: Int
        get() = _trajetsÀVenir.size
}