package crosemont.dti.g55.applicationallezhop.Vue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import crosemont.dti.g55.applicationallezhop.Présentateur.PrésentateurAccueil
import crosemont.dti.g55.applicationallezhop.Présentateur.PrésentateurTrajet

import crosemont.dti.g55.applicationallezhop.R

/**
 * A simple [Fragment] subclass.
 * Use the [vue_trajet.newInstance] factory method to
 * create an instance of this fragment.
 */
class vue_trajet : Fragment() {
    lateinit var navController: NavController
    var présentateurTrajet = PrésentateurTrajet(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Déclaration de la vue afin de le lier au layout
        val vue = inflater.inflate(R.layout.fragment_vue_trajet, container, false)

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
                    présentateurTrajet.effectuerNavigationAccueil()
                    true
                }
                R.id.menu_profil -> {
                    présentateurTrajet.effectuerNavigationProfil()
                    true
                }
                R.id.menu_trajet -> true
                else -> false
            }
        }

// Mettez en surbrillance l'élément correspondant dans le BottomNavigationView
        when (navController.currentDestination?.id) {
            R.id.vue_accueil -> bottomNavigationView.menu.findItem(R.id.menu_accueil).isChecked = true
            R.id.vue_profil -> bottomNavigationView.menu.findItem(R.id.menu_profil).isChecked = true
            R.id.vue_trajet -> bottomNavigationView.menu.findItem(R.id.menu_trajet).isChecked = true
        }
    }

    fun naviguerVerVueProfil(){
        navController.navigate(R.id.action_vue_trajet_to_vue_profil)
    }
    fun naviguerVerVueAccueil(){
        navController.navigate(R.id.action_vue_trajet_to_vue_accueil)
    }



}