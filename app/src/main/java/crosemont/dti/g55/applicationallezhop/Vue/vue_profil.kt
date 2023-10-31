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
import crosemont.dti.g55.applicationallezhop.Présentateur.PrésentateurAccueil
import crosemont.dti.g55.applicationallezhop.Présentateur.PrésentateurProfil
import crosemont.dti.g55.applicationallezhop.R

/**
 * A simple [Fragment] subclass.
 * Use the [vue_profil.newInstance] factory method to
 * create an instance of this fragment.
 */
class vue_profil : Fragment() {
    lateinit var navController: NavController
    lateinit var  btnProfilAccueil : Button
    lateinit var  btnProfilTrajet : Button
    var présentateurProfil = PrésentateurProfil(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Déclaration de la vue afin de le lier au layout
        val vue = inflater.inflate(R.layout.fragment_vue_profil, container, false)

        btnProfilTrajet = vue.findViewById(R.id.btnProfilTrajet)
        btnProfilTrajet.setOnClickListener(){
            présentateurProfil.effectuerNavigationTrajet()
        }
        btnProfilAccueil = vue.findViewById(R.id.btnProfilAccueil)
        btnProfilAccueil.setOnClickListener(){
            présentateurProfil.effectuerNavigationAccueil()
        }

        // Inflate the layout for this fragment
        return vue
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtient le NavController pour la navigation
        navController = Navigation.findNavController(view)
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_accueil -> {
                    présentateurProfil.effectuerNavigationAccueil()
                    true
                }
                R.id.menu_trajet -> {
                    présentateurProfil.effectuerNavigationTrajet()
                    true
                }
                R.id.menu_profil -> true
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
    fun naviguerVerVueAccueil(){
        navController.navigate(R.id.action_vue_profil_to_vue_accueil)
    }
    fun naviguerVerVueTrajet(){
        navController.navigate(R.id.action_vue_profil_to_vue_trajet)
    }

}