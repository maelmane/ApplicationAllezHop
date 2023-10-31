package crosemont.dti.g55.applicationallezhop.Vue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
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
    lateinit var  btnTrajetProfil : Button
    lateinit var  btnTrajetAccueil : Button
    var présentateurTrajet = PrésentateurTrajet(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Déclaration de la vue afin de le lier au layout
        val vue = inflater.inflate(R.layout.fragment_vue_trajet, container, false)
        btnTrajetProfil = vue.findViewById(R.id.btnTrajetProfil)
        btnTrajetProfil.setOnClickListener(){
            présentateurTrajet.effectuerNavigationProfil()
        }
        btnTrajetAccueil = vue.findViewById(R.id.btnTrajetAccueil)
        btnTrajetAccueil.setOnClickListener(){
            présentateurTrajet.effectuerNavigationAccueil()
        }

        // Inflate the layout for this fragment
        return vue
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtient le NavController pour la navigation
        navController = Navigation.findNavController(view)
    }

    fun naviguerVerVueProfil(){
        navController.navigate(R.id.action_vue_trajet_to_vue_profil)
    }
    fun naviguerVerVueAccueil(){
        navController.navigate(R.id.action_vue_trajet_to_vue_accueil)
    }



}