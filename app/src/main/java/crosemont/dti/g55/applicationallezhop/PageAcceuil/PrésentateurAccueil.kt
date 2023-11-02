package crosemont.dti.g55.applicationallezhop.PageAcceuil

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
