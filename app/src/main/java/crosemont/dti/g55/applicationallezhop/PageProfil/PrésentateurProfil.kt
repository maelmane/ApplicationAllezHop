package crosemont.dti.g55.applicationallezhop.PageProfil
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceDeDonnées

import crosemont.dti.g55.applicationallezhop.Modèle.Trajet

class PrésentateurProfil(var vue: vue_profil, var sourceDeDonnées: SourceDeDonnées) {
    //    Méthode pour effectuer la navigation vers l'écran des accueil
    fun effectuerNavigationAccueil() {
        vue.naviguerVerVueAccueil()
    }
    //    Méthode pour effectuer la navigation vers l'écran des trajet
    fun effectuerNavigationTrajet() {
        vue.naviguerVerVueTrajet()
    }


    fun getTrajetsVenirData(): List<Trajet> {
        return sourceDeDonnées.getTrajetsVenirData()
    }

    fun getTrajetsAnciensData(): List<Trajet> {
        return sourceDeDonnées.getTrajetsAnciensData()
    }


}