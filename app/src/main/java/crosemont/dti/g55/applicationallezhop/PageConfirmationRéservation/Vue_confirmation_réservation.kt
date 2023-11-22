package crosemont.dti.g55.applicationallezhop.PageConfirmationRéservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import crosemont.dti.g55.applicationallezhop.PageConfirmationRéservation.PrésentateurConfirmationRéservation
import crosemont.dti.g55.applicationallezhop.R

class vue_confirmation_réservation  : Fragment() {
    lateinit var navController: NavController
    var présentateurConfirmationRéservation = PrésentateurConfirmationRéservation(this)
    lateinit var btnConfirmationRéservation : Button
    lateinit var txtConducteur : EditText
    lateinit var txtHeureArriver : EditText
    lateinit var txtAdresseEmbarcation : EditText
    lateinit var conducteur :String
    lateinit var AddresseEmbarcation :String
    lateinit var HeureArrivé :String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Déclaration de la vue afin de le lier au layout
        val vue = inflater.inflate(R.layout.fragment_vue_confirmation_reservation, container, false)

        // Inflate the layout for this fragment
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtient le NavController pour la navigation
        navController = Navigation.findNavController(view)
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        bottomNavigationView.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_accueil -> {
                    naviguerVerVueAccueil()
                    true
                }

                R.id.menu_trajet -> {
                    naviguerVerVueTrajet()
                    true
                }

                R.id.menu_profil -> true
                else -> false
            }
        }

        // Mettez en surbrillance l'élément correspondant dans le BottomNavigationView
        when (navController.currentDestination?.id) {
            R.id.vue_accueil -> bottomNavigationView.menu.findItem(R.id.menu_accueil).isChecked =
                true

            R.id.vue_profil -> bottomNavigationView.menu.findItem(R.id.menu_profil).isChecked = true
            R.id.vue_trajet -> bottomNavigationView.menu.findItem(R.id.menu_trajet).isChecked = true
        }



        this.arguments?.let {
            conducteur = it.getString("Conducteur")!!
            AddresseEmbarcation = it.getString("AddresseEmbarcation")!!
            HeureArrivé = it.getString("HeureArrivé")!!
        }
        txtConducteur = view.findViewById(R.id.txt_conducteur)
        txtAdresseEmbarcation = view.findViewById(R.id.txt_adresse_embarcation)
        txtHeureArriver = view.findViewById(R.id.txt_heure_arriver)

        txtConducteur.setText(conducteur)
        txtAdresseEmbarcation.setText(AddresseEmbarcation)
        txtHeureArriver.setText(HeureArrivé)


        val bundleForProfil = Bundle().apply {
            putString("Conducteur", conducteur)
            putString("AddresseEmbarcation", AddresseEmbarcation)
            putString("HeureArrivé", HeureArrivé)
        }



        btnConfirmationRéservation = view.findViewById(R.id.btn_confirmer_reservation)
        btnConfirmationRéservation.setOnClickListener { naviguerVerVueProfil(bundleForProfil) }


    }

    fun naviguerVerVueAccueil(){
        navController.navigate(R.id.action_vue_confirmation_reservation_to_vue_accueil)
    }

    fun naviguerVerVueProfil(bundle: Bundle) {
        navController.navigate(R.id.action_vue_confirmation_réservation_to_vue_profil, bundle)
    }

    fun naviguerVerVueTrajet(){
        navController.navigate(R.id.action_vue_confirmation_reservation_to_vue_trajet)
    }

}