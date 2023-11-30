    package crosemont.dti.g55.applicationallezhop.PageConfirmationRéservation

    import android.os.Bundle
    import android.util.Log
    import crosemont.dti.g55.applicationallezhop.Modèle.Adresse
    import crosemont.dti.g55.applicationallezhop.Modèle.ModèleTrajet
    import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
    import crosemont.dti.g55.applicationallezhop.Modèle.Voiture
    import crosemont.dti.g55.applicationallezhop.PageProfil.ProfilAdapter
    import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon
    import java.text.SimpleDateFormat
    import java.util.Locale

    class PrésentateurConfirmationRéservation(var vue: vue_confirmation_réservation): IPrésentateurConfirmationRéservation {

        var modèle = ModèleTrajet(SourceBidon.getInstance())

        fun ajoutéTrajetVenir( date: String, destination: Adresse, conducteur: String, heureArriver: String, heureDépart: String, voiture: Voiture){
            modèle.réserver(Trajet(date, destination, conducteur,heureArriver,heureDépart,voiture))
            //modèle._trajetsÀVenir.add(Trajet(date, destination, conducteur,heureArriver,heureDépart,voiture))

        }

        fun convertirDateHeureEnMillis(dateStr: String, heureStr: String): Long {
            val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val dateHeure = "$dateStr $heureStr"
            return format.parse(dateHeure)?.time ?: 0
        }

    }