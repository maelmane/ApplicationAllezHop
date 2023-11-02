package crosemont.dti.g55.applicationallezhop.PageTrajet

import crosemont.dti.g55.applicationallezhop.PageTrajet.vue_trajet

class PrésentateurTrajet(var vue : vue_trajet) {
    //    Méthode pour effectuer la navigation vers l'écran des profil
    fun effectuerNavigationProfil() {
        vue.naviguerVerVueProfil()
    }
    //    Méthode pour effectuer la navigation vers l'écran des trajet
    fun effectuerNavigationAccueil() {
        vue.naviguerVerVueAccueil()
    }

    fun effectuerNavigationConfirmation() {
        vue.naviguerVerVueConfirmationRéservation()
    }
}