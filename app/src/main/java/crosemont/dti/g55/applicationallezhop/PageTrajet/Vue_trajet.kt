package crosemont.dti.g55.applicationallezhop.PageTrajet

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.PageProfil.ProfilAdapter
import crosemont.dti.g55.applicationallezhop.R


/**
 * A simple [Fragment] subclass.
 * Use the [vue_trajet.newInstance] factory method to
 * create an instance of this fragment.
 */
class vue_trajet : Fragment() {
    lateinit var navController: NavController
    lateinit var btnRéserver : Button
    var présentateurTrajet = PrésentateurTrajet(this)
    lateinit var reloadButton: ImageButton
    lateinit var Destination : TextInputLayout
    lateinit var Position : TextInputLayout
    private lateinit var recyclerViewTrajet: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Déclaration de la vue afin de le lier au layout
        val vue = inflater.inflate(R.layout.fragment_vue_trajet, container, false)


        reloadButton = vue.findViewById(R.id.reload)
        reloadButton.setImageResource(android.R.drawable.ic_popup_sync)
        reloadButton.setColorFilter(ContextCompat.getColor(requireContext(), android.R.color.black))



        reloadButton.setOnClickListener {
            reloadButton()
        }
        // Inflate the layout for this fragment
        return vue




    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Destination = view.findViewById(R.id.Destination)
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
                R.id.menu_profil -> {
                    naviguerVerVueProfil()
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

//        btnRéserver = view.findViewById(R.id.btnRéserver)
//        btnRéserver.setOnClickListener { naviguerVerVueConfirmationRéservation() }

        recyclerViewTrajet= view.findViewById(R.id.recyclerViewTrajets)
        setUpRecyclerView(recyclerViewTrajet, présentateurTrajet.getTrajetsVenirData())

    }

    fun naviguerVerVueProfil(){
        navController.navigate(R.id.action_vue_trajet_to_vue_profil)
    }
    fun naviguerVerVueAccueil(){
        navController.navigate(R.id.action_vue_trajet_to_vue_accueil)
    }

    fun naviguerVerVueConfirmationRéservation(){
        navController.navigate(R.id.action_vue_trajet_to_vue_confirmation_reservation)
    }

    fun reloadButton(){
        // Assurez-vous que Destination est initialisé correctement
        Destination = view?.findViewById(R.id.Destination) ?: return
        Position = view?.findViewById(R.id.Position) ?: return
        val textDestination = Destination.editText?.text.toString()
        val textPosition = Position.editText?.text.toString()

        // Affichez le texte dans un Toast pour le test
        Toast.makeText(requireContext(), "Cliqué", Toast.LENGTH_SHORT).show()

        // Transfert du texte
        Position.editText?.setText(textDestination)
        Destination.editText?.setText(textPosition)

    }

    fun setUpRecyclerView(recyclerView: RecyclerView, data: List<Trajet>) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = TrajetAdapter(data)
    }



}