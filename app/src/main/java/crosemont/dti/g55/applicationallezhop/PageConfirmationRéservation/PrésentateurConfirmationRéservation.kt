    package crosemont.dti.g55.applicationallezhop.PageConfirmationRéservation

    import android.os.Bundle
    import android.util.Log
    import crosemont.dti.g55.applicationallezhop.Modèle.ModèleTrajet
    import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
    import crosemont.dti.g55.applicationallezhop.PageProfil.ProfilAdapter
    import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon

    class PrésentateurConfirmationRéservation(var vue: vue_confirmation_réservation): IPrésentateurConfirmationRéservation {

        var modèle = ModèleTrajet(SourceBidon())
    }