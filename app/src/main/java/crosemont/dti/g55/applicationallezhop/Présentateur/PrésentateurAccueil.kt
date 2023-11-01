package crosemont.dti.g55.applicationallezhop.Présentateur

import crosemont.dti.g55.applicationallezhop.Vue.vue_accueil

class PrésentateurAccueil(var vue: vue_accueil) {
    //    Méthode pour effectuer la navigation vers l'écran des profil
    fun effectuerNavigationProfil() {
        vue.naviguerVerVueProfil()
    }
    //    Méthode pour effectuer la navigation vers l'écran des trajet
    fun effectuerNavigationTrajet() {
        vue.naviguerVerVueTrajet()
    }
//
}
