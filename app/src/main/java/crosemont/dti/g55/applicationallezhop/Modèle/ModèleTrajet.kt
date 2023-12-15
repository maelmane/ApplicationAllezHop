package crosemont.dti.g55.applicationallezhop.Modèle

import android.util.Log
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceDeDonnées
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceDeDonnéesHTTP
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class ModèleTrajet(var sourceDeDonnées: SourceDeDonnées) {
    var _trajetsÀVenir = mutableListOf<Trajet>()
    private var _trajets =mutableListOf<Trajet>()
    private var sourceHttp = SourceDeDonnéesHTTP("https://0898b9b0-2661-4bde-95e6-4ca06e12bef0.mock.pstmn.io")

    suspend fun obtenirTrajetÀVenir(indice : Int) : Trajet{
        _trajets = chargerTrajets()
        return _trajets[indice]
    }

    suspend fun chargerTrajetsÀVenir() : MutableList<Trajet>{
        if (_trajetsÀVenir.isEmpty()){
            _trajets = chargerTrajets()
            _trajetsÀVenir = _trajets
        }

        /*Log.d("twest","$_trajets")
        for(trajet in _trajets){
            if(dateAjourdhui(trajet.date)){
                _trajetsÀVenir.add(trajet)
            }
        }
        Log.d("twest","$_trajetsÀVenir")*/
        Log.d("test", "$_trajets")
        return _trajetsÀVenir
    }
    suspend fun chargerTrajets(): MutableList<Trajet>  {
        return sourceHttp.getTrajetsAnciensData("https://0898b9b0-2661-4bde-95e6-4ca06e12bef0.mock.pstmn.io/trajets")
    }

    fun réserver(trajet: Trajet){
        sourceDeDonnées.créer(trajet)
    }
    private fun dateAjourdhui(dateString: String): Boolean {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val dateActuel = Date()
        val date = dateFormat.parse(dateString)

        return date == dateActuel
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
    fun filtrerTrajetsSelonHeure(heure: String): MutableList<Trajet>{
        var listeTrajetsFiltrés = mutableListOf<Trajet>()
        for (trajet in _trajetsÀVenir){
            if (trajet.heureArriver == heure){
                listeTrajetsFiltrés.add(trajet)
            }
        }
        _trajetsÀVenir = listeTrajetsFiltrés
        return listeTrajetsFiltrés
    }

    fun filtrerTrajetsSelonAdresse(adresse: String): MutableList<Trajet>{
        var listeTrajetsFiltrés = mutableListOf<Trajet>()
        for (trajet in _trajetsÀVenir){
            if (trajet.destination.toString().contains(adresse)){
                listeTrajetsFiltrés.add(trajet)
            }
        }
        _trajetsÀVenir = listeTrajetsFiltrés
        return listeTrajetsFiltrés
    }

    val tailleTrajetsÀVenir: Int
        get() = _trajetsÀVenir.size

}