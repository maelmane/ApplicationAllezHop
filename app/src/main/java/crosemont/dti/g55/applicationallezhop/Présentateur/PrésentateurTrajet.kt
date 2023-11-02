package crosemont.dti.g55.applicationallezhop.Présentateur

import crosemont.dti.g55.applicationallezhop.Vue.vue_trajet

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