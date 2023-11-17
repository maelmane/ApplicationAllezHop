package crosemont.dti.g55.applicationallezhop.PageConfirmationRéservation

import crosemont.dti.g55.applicationallezhop.Modèle.ModèleTrajet
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon

class PrésentateurConfirmationRéservation(var vue: vue_confirmation_réservation) {

    var modèle = ModèleTrajet(SourceBidon())



}