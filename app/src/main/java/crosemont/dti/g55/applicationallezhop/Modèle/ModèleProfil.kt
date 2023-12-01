package crosemont.dti.g55.applicationallezhop.Modèle

import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceDeDonnées
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceDeDonnéesHTTP
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class ModèleProfil(var sourceDeDonnées: SourceDeDonnées) {
    private var _trajetsÀVenir = mutableListOf<Trajet>()
    private var _trajets = mutableListOf<Trajet>()
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
        _trajets  = chargerTrajets()
        for(trajet in _trajets){
            if(datePasser(trajet.date)){
                _anciensTrajets.add(trajet)
            }
        }
        return _anciensTrajets
    }

    suspend fun chargerTrajets(): MutableList<Trajet>  {
        return sourceHttp.getTrajetsAnciensData("https://0898b9b0-2661-4bde-95e6-4ca06e12bef0.mock.pstmn.io/trajets")
    }


    val tailleTrajetsÀVenir: Int
        get() = _trajetsÀVenir.size

    val tailleAnciensTrajets: Int
        get() = _anciensTrajets.size

    fun addTrajetVenir(trajet: Trajet) {
        _trajetsÀVenir.add(trajet)
    }

    private fun datePasser(dateString: String): Boolean {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val dateActuel = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time
        val date = dateFormat.parse(dateString)

        return date.before(dateActuel)
    }
}