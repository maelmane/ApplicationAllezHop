package crosemont.dti.g55.applicationallezhop.PageProfil

class PrésentateurProfil(var vue : vue_profil) {
    //    Méthode pour effectuer la navigation vers l'écran des accueil
    fun effectuerNavigationAccueil() {
        vue.naviguerVerVueAccueil()
    }
    //    Méthode pour effectuer la navigation vers l'écran des trajet
    fun effectuerNavigationTrajet() {
        vue.naviguerVerVueTrajet()
    }
//    //    Méthode pour effectuer la navigation vers l'écran des profil
//    fun effectuerNavigationProfil() {
//        vue.naviguerVerVueProfil()
//    }
}