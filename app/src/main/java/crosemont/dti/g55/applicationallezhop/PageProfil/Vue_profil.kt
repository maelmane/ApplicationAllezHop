package crosemont.dti.g55.applicationallezhop.PageProfil

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.Modèle.Voiture
import crosemont.dti.g55.applicationallezhop.PageTrajet.TrajetAdapter
import crosemont.dti.g55.applicationallezhop.R
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [vue_profil.newInstance] factory method to
 * create an instance of this fragment.
 */
class vue_profil : Fragment() {
    lateinit var navController: NavController
    private var présentateurProfil = PrésentateurProfil(this)
    private lateinit var recyclerViewTrajetsVenir: RecyclerView
    private lateinit var recyclerViewTrajetsAnciens: RecyclerView
    private lateinit var chercherTrajetVenirJob : Job
    private lateinit var chercherTrajetAncienJob : Job
    private lateinit var afficherTrajetAncienJob : Job

    private var _adapterVenir: ProfilAdapter? = null
    private var _adapterAnciens: ProfilAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vue_profil, container, false)
        recyclerViewTrajetsVenir = view.findViewById(R.id.recyclerViewTrajetsVenir)
        recyclerViewTrajetsAnciens = view.findViewById(R.id.recyclerViewTrajetsAnciens)

        chercherTrajetAncienJob = CoroutineScope(Dispatchers.IO).launch {
            var data = présentateurProfil.getTrajetsAnciensData()
            afficherTrajetAncienJob =CoroutineScope(Dispatchers.Main).launch { setUpRecyclerView(recyclerViewTrajetsAnciens, data) }

        }
        chercherTrajetVenirJob = CoroutineScope(Dispatchers.IO).launch {
            setUpRecyclerView(recyclerViewTrajetsVenir, présentateurProfil.getTrajetsVenirData())
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtain NavController for navigation
        navController = Navigation.findNavController(view)
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Set up BottomNavigationView with NavController
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        bottomNavigationView.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_accueil -> {
                    if(isNetworkAvailable()){
                        naviguerVerVueAccueil()
                    }
                    else{
                        navController.navigate(R.id.action_vue_profil_to_pasInternetLayout)
                    }
                    true
                }
                R.id.menu_trajet -> {
                    if(isNetworkAvailable()){
                        naviguerVerVueTrajet()
                    }
                    else{
                        navController.navigate(R.id.action_vue_profil_to_pasInternetLayout)
                    }
                    true
                }
                R.id.menu_profil -> true
                else -> false
            }
        }

        when (navController.currentDestination?.id) {
            R.id.vue_accueil -> bottomNavigationView.menu.findItem(R.id.menu_accueil).isChecked = true
            R.id.vue_profil -> bottomNavigationView.menu.findItem(R.id.menu_profil).isChecked = true
            R.id.vue_trajet -> bottomNavigationView.menu.findItem(R.id.menu_trajet).isChecked = true
        }
        if (!isNetworkAvailable()) {
            // Naviguer vers PasInternetLayout lorsque l'Internet n'est pas disponible
            navController.navigate(R.id.action_vue_profil_to_pasInternetLayout)
        }
    }
    private fun isNetworkAvailable(): Boolean {
        val cm = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
    // Navigation functions
    fun naviguerVerVueAccueil() {
        navController.navigate(R.id.action_vue_profil_to_vue_accueil)
    }

    fun naviguerVerVueTrajet() {
        navController.navigate(R.id.action_vue_profil_to_vue_trajet)
    }
    private fun setUpRecyclerView(recyclerView: RecyclerView, data: List<Trajet>) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        when (recyclerView.id) {
            R.id.recyclerViewTrajetsVenir -> {
                _adapterVenir = ProfilAdapter(data)

                recyclerView.adapter = _adapterVenir

                Log.d("vue_profil", "RecyclerViewTrajetsVenir contents: ${data}")
            }
            R.id.recyclerViewTrajetsAnciens -> {
                _adapterAnciens = ProfilAdapter(data)
                recyclerView.adapter = _adapterAnciens

                Log.d("vue_profil", "RecyclerViewTrajetsAnciens contents: ${data}")
            }
        }
    }

    suspend fun rafraîchirAffichage() {
        _adapterVenir?.setData(présentateurProfil.getTrajetsVenirData())
    }

}
