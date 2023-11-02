package crosemont.dti.g55.applicationallezhop.Présentateur

import crosemont.dti.g55.applicationallezhop.Vue.vue_confirmation_reservation

class PrésentateurConfirmationRéservation(var vue: vue_confirmation_reservation) {

    fun effectuerNavigationAccueil() {
        vue.naviguerVerVueAccueil()
    }

    fun effectuerNavigationTrajet() {
        vue.naviguerVerVueTrajet()
    }
}