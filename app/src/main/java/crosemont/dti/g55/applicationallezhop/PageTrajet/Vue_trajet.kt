package crosemont.dti.g55.applicationallezhop.PageTrajet

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import kotlin.math.log


/**
 * A simple [Fragment] subclass.
 * Use the [vue_trajet.newInstance] factory method to
 * create an instance of this fragment.
 */
class vue_trajet : Fragment() {

    lateinit var reloadButton: ImageButton
    lateinit var Destination : TextInputLayout
    lateinit var Position : TextInputLayout
    lateinit var selectedTimeTV: TextView
    lateinit var selectedDateTV: TextView
    lateinit var destinationET: EditText
    /*private lateinit var chercherTrajetVenirJob : Job
    private lateinit var afficherTrajetVenirJob : Job*/
    var présentateurTrajet = PrésentateurTrajet(this)
    lateinit var navController: NavController
    private lateinit var recyclerViewTrajet: RecyclerView
    private var _adapter : TrajetAdapter? = null


    suspend fun rafraîchir() {
        _adapter!!.setData(présentateurTrajet.getTrajetsVenirData())
    }

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
        val clipboardManager = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        destinationET = vue.findViewById(R.id.txtiearriverPosition)
        destinationET.doAfterTextChanged {
            //Log.d("adresse", savedFavoriteAddresses.toString())
            présentateurTrajet.filtrerSelonAdresse(destinationET.text.toString())
        }

        selectedTimeTV = vue.findViewById(R.id.idTVSelectedTime)
        var formatTemps = DateTimeFormatter.ofPattern("HH:mm")
        selectedTimeTV.text = LocalTime.now().format(formatTemps)

        selectedTimeTV.setOnClickListener {
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                context,
                { view, hourOfDay, minute ->
                    selectedTimeTV.setText((hourOfDay.toString()).padStart(2, '0')+":"+ (minute.toString()).padStart(2, '0'))
                },
                hour,
                minute,
                true
            )
            timePickerDialog.show()
        }

        selectedDateTV = vue.findViewById(R.id.idTVSelectedDate)
        var formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        selectedDateTV.text = LocalDate.now().format(formatDate)

        selectedDateTV.setOnClickListener {
            val c = Calendar.getInstance()

            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            var zero =""
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                requireContext(),
                { view, year, monthOfYear, dayOfMonth  ->
                    // on below line we are setting
                    // date to our text view.
                    if(dayOfMonth<10){
                        zero = "0"
                    }
                    selectedDateTV.text =
                        (year.toString() + "-" + (monthOfYear + 1) + "-"+ (dayOfMonth.toString()).padStart(2, '0'))
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )

            datePickerDialog.show()
        }

        selectedDateTV.doAfterTextChanged {
            Log.d("Temps", selectedDateTV.text.toString())
            présentateurTrajet.filtrerSelonDate(selectedDateTV.text.toString())
        }
        selectedTimeTV.doAfterTextChanged {
            Log.d("Temps", selectedTimeTV.text.toString())
            présentateurTrajet.filtrerSelonHeure(selectedTimeTV.text.toString())
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
                    if(isNetworkAvailable()){
                        naviguerVerVueAccueil()
                    }
                    else{
                        navController.navigate(R.id.action_vue_trajet_to_pasInternetLayout)
                    }
                    true
                }
                R.id.menu_profil -> {
                    if(isNetworkAvailable()){
                        naviguerVerVueProfil()
                    }
                    else{
                        navController.navigate(R.id.action_vue_trajet_to_pasInternetLayout)
                    }
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

        recyclerViewTrajet= view.findViewById(R.id.recyclerViewTrajets)
        rafraichir()
        if (!isNetworkAvailable()) {
            // Naviguer vers PasInternetLayout lorsque l'Internet n'est pas disponible
            navController.navigate(R.id.action_vue_trajet_to_pasInternetLayout)
        }
    }
    private fun isNetworkAvailable(): Boolean {
        val cm = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
    fun naviguerVerVueProfil(){
        navController.navigate(R.id.action_vue_trajet_to_vue_profil)
    }
    fun naviguerVerVueAccueil(){
        navController.navigate(R.id.action_vue_trajet_to_vue_accueil)
    }

    fun naviguerVerVueConfirmationRéservation(bundle: Bundle){
        navController.navigate(R.id.action_vue_trajet_to_vue_confirmation_reservation, bundle)
    }

    fun reloadButton(){
        // Assurez-vous que Destination est initialisé correctement
        Destination = view?.findViewById(R.id.Destination) ?: return
        Position = view?.findViewById(R.id.Position) ?: return
        val textDestination = Destination.editText?.text.toString()
        val textPosition = Position.editText?.text.toString()

        // Transfert du texte
        Position.editText?.setText(textDestination)
        Destination.editText?.setText(textPosition)

    }

    fun setUpRecyclerView(recyclerView: RecyclerView, data: List<Trajet>) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        _adapter = TrajetAdapter(présentateurTrajet, data)
        recyclerView.adapter = _adapter
    }

    fun rafraichir(){

        /*chercherTrajetVenirJob= */CoroutineScope(Dispatchers.IO).launch {
            var data = présentateurTrajet.getTrajetsVenirData()

            /* afficherTrajetVenirJob =*/ CoroutineScope(Dispatchers.Main).launch { setUpRecyclerView(recyclerViewTrajet, data) }
        }
    }

    fun créerClipBoard(adresses: List<String>){
        val clip = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        for (i in adresses){
            val clipData = ClipData.newPlainText("adresse", i)
            clip.setPrimaryClip(clipData)
        }
    }

    fun chercherFavoris(): List<String>{
        val sharedPreferences = requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val savedFavoriteAddressesJson = sharedPreferences.getString("favoriteAddresses", null)
        val savedFavoriteAddresses = if (savedFavoriteAddressesJson != null) {
            Gson().fromJson<List<String>>(
                savedFavoriteAddressesJson,
                object : TypeToken<List<String>>() {}.type
            )
        } else {
            emptyList()
        }
        return savedFavoriteAddresses
    }



}