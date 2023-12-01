package crosemont.dti.g55.applicationallezhop.PageConfirmationRéservation

import android.content.Intent
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import crosemont.dti.g55.applicationallezhop.Modèle.Adresse
import crosemont.dti.g55.applicationallezhop.Modèle.ModèleProfil
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.Modèle.Voiture
import crosemont.dti.g55.applicationallezhop.PageConfirmationRéservation.PrésentateurConfirmationRéservation
import crosemont.dti.g55.applicationallezhop.PageProfil.ProfilAdapter
import crosemont.dti.g55.applicationallezhop.PageProfil.vue_profil
import crosemont.dti.g55.applicationallezhop.R
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class vue_confirmation_réservation  : Fragment() {
    lateinit var navController: NavController
    var présentateurConfirmationRéservation = PrésentateurConfirmationRéservation(this)
    lateinit var btnConfirmationRéservation : Button
    lateinit var txtConducteur : TextView
    lateinit var txtHeureArriver : TextView
    lateinit var txtAdresseEmbarcation : TextView
    lateinit var conducteur :String
    lateinit var destination : Adresse
    lateinit var heureArrivé :String
    private lateinit var créerTrajetJob : Job

    private var _adapter: ProfilAdapter? = null

    lateinit var date: String
    lateinit var heureDepart: String
    lateinit var voiture: Voiture

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
                    naviguerVerVueAccueil(savedInstanceState!!)
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
            destination = it.getSerializable("Destination") as Adresse
            heureArrivé = it.getString("HeureArrivé")!!
            date = it.getString("Date")!!
            heureDepart = it.getString("HeureDépart")!!
            voiture = it.getSerializable("Voiture") as Voiture
        }
        txtConducteur = view.findViewById(R.id.txt_conducteur)
        txtAdresseEmbarcation = view.findViewById(R.id.txt_adresse_embarcation)
        txtHeureArriver = view.findViewById(R.id.txt_heure_arriver)

        txtConducteur.setText(conducteur)
        txtAdresseEmbarcation.setText(destination.toString())
        txtHeureArriver.setText(heureArrivé)

        val bundleForProfil = Bundle().apply {
            putString("Conducteur", conducteur)
            putSerializable("Destination", destination)
            putString("HeureArrivé", heureArrivé)
            putString("Date", date)
            putString("heureDepart", heureDepart)
            putSerializable("Voiture", voiture)
        }


        btnConfirmationRéservation = view.findViewById(R.id.btn_confirmer_reservation)
        btnConfirmationRéservation.setOnClickListener {
            créerTrajetJob = CoroutineScope(Dispatchers.IO).launch { présentateurConfirmationRéservation.ajoutéTrajetVenir(date,destination, conducteur,heureArrivé, heureDepart,voiture) }
            val intent = Intent(Intent.ACTION_INSERT)
                .setData(Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, présentateurConfirmationRéservation.convertirDateHeureEnMillis(date,heureDepart))
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, présentateurConfirmationRéservation.convertirDateHeureEnMillis(date,heureArrivé))
                .putExtra(Events.TITLE, "Covoiturage AllezHop")
                .putExtra(Events.DESCRIPTION, "Conducteur : $conducteur")
                .putExtra(Events.EVENT_LOCATION, "$destination")
                .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY)


            startActivity(intent)
            CoroutineScope(Dispatchers.Main).launch { naviguerVerVueProfil(bundleForProfil) }

        }
    }

    fun naviguerVerVueAccueil(bundle: Bundle){
        navController.navigate(R.id.action_vue_confirmation_reservation_to_vue_accueil, bundle)
    }

    fun naviguerVerVueProfil(bundle: Bundle) {
        navController.navigate(R.id.action_vue_confirmation_réservation_to_vue_profil, bundle)

    }

    fun naviguerVerVueTrajet(){
        navController.navigate(R.id.action_vue_confirmation_reservation_to_vue_trajet)
    }


}