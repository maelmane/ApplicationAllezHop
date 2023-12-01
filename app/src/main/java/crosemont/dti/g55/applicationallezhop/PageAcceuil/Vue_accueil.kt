package crosemont.dti.g55.applicationallezhop.PageAcceuil

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import crosemont.dti.g55.applicationallezhop.Modèle.Adresse
import crosemont.dti.g55.applicationallezhop.PageAcceuil.PrésentateurAccueil
import crosemont.dti.g55.applicationallezhop.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 * Use the [vue_accueil.newInstance] factory method to
 * create an instance of this fragment.
 */
class vue_accueil : Fragment(), OnMapReadyCallback {
    lateinit var navController: NavController
    var présentateurAccueil = PrésentateurAccueil(this)
    lateinit var map : FrameLayout
    lateinit var adresse: String
    lateinit var destination : Adresse
    lateinit var trajetActuelJob : Job
    lateinit var trajetActuelAffichageJob : Job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Déclaration de la vue afin de le lier au layout
        val vue = inflater.inflate(R.layout.fragment_vue_accueil, container, false)
        map = vue.findViewById(R.id.map)
        var mapFragment = childFragmentManager.findFragmentById(R.id.map)
        val supportMapFragment = mapFragment as? SupportMapFragment
        supportMapFragment?.getMapAsync(this)
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
                R.id.menu_profil -> {
                    naviguerVerVueProfil()
                    true
                }
                R.id.menu_trajet -> {
                    naviguerVerVueTrajet()
                    true
                }
                R.id.menu_accueil -> true
                else -> false
            }
        }

// Mettez en surbrillance l'élément correspondant dans le BottomNavigationView
        when (navController.currentDestination?.id) {
            R.id.vue_accueil -> bottomNavigationView.menu.findItem(R.id.menu_accueil).isChecked = true
            R.id.vue_profil -> bottomNavigationView.menu.findItem(R.id.menu_profil).isChecked = true
            R.id.vue_trajet -> bottomNavigationView.menu.findItem(R.id.menu_trajet).isChecked = true
        }

        this.arguments?.let{
            destination = it.getSerializable("Destination") as Adresse
        }


    }

    fun naviguerVerVueProfil(){
        navController.navigate(R.id.action_vue_accueil_to_vue_profil)
    }

    fun naviguerVerVueTrajet(){
        navController.navigate(R.id.action_vue_accueil_to_vue_trajet)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // Mettre les coordonnées à la destination
        /*trajetActuelJob = CoroutineScope(Dispatchers.IO).launch {
            adresse = présentateurAccueil.getAdresseDestination()

            var latLngDestination = présentateurAccueil.getLatitudeLongitudeAdresse(adresse)
            trajetActuelAffichageJob = CoroutineScope(Dispatchers.Main).launch {
                // Mettre le type de la map en hybride.
                googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID

                // Ajouter un marqueur aux coordonnées
                googleMap.addMarker(
                    MarkerOptions()
                        .position(latLngDestination)
                        .title("Destination")
                )

                // Faire bouger la caméra vers les coordonnées et zoomer
                googleMap.moveCamera(CameraUpdateFactory.zoomTo(15f))
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLngDestination))


                // Afficher le trafic
                googleMap.isTrafficEnabled = true
            }
        }*/
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val adresse = withContext(Dispatchers.IO) {
                    présentateurAccueil.getAdresseDestination()
                }

                val latLngDestination = withContext(Dispatchers.IO) {
                    présentateurAccueil.getLatitudeLongitudeAdresse(adresse)
                }

                // Mettre le type de la map en hybride.
                googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID

                // Ajouter un marqueur aux coordonnées
                googleMap.addMarker(
                    MarkerOptions()
                        .position(latLngDestination)
                        .title("Destination")
                )

                // Faire bouger la caméra vers les coordonnées et zoomer
                googleMap.moveCamera(CameraUpdateFactory.zoomTo(15f))
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLngDestination))

                // Afficher le trafic
                googleMap.isTrafficEnabled = true
            } catch (e: Exception) {
                // Gérer les erreurs, par exemple, en affichant un message d'erreur
                e.printStackTrace()
            }
        }
    }
}
