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
import crosemont.dti.g55.applicationallezhop.R

/**
 * A simple [Fragment] subclass.
 * Use the [vue_accueil.newInstance] factory method to
 * create an instance of this fragment.
 */
class vue_accueil : Fragment() {
    lateinit var navController: NavController
    lateinit var  btnProfil : Button
    lateinit var  btnTrajet : Button
    var présentateurAccueil = PrésentateurAccueil(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Déclaration de la vue afin de le lier au layout
        val vue = inflater.inflate(R.layout.fragment_vue_accueil, container, false)
        btnProfil = vue.findViewById(R.id.btnAccueilProfil)
        btnProfil.setOnClickListener(){
            présentateurAccueil.effectuerNavigationProfil()
        }
        btnTrajet = vue.findViewById(R.id.btnAccueilTrajet)
        btnTrajet.setOnClickListener(){
            présentateurAccueil.effectuerNavigationTrajet()
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
        navController.navigate(R.id.action_vue_accueil_to_vue_profil)
    }
    fun naviguerVerVueTrajet(){
        navController.navigate(R.id.action_vue_accueil_to_vue_trajet)
    }




}