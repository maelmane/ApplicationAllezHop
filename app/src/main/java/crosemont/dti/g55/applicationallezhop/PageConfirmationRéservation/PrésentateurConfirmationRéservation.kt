package crosemont.dti.g55.applicationallezhop.PageConfirmationRéservation

class PrésentateurConfirmationRéservation(var vue: vue_confirmation_réservation) {

    fun effectuerNavigationAccueil() {
        vue.naviguerVerVueAccueil()
    }

    fun effectuerNavigationTrajet() {
        vue.naviguerVerVueTrajet()
    }
}