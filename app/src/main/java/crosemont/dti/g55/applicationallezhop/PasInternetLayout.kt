package crosemont.dti.g55.applicationallezhop

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PasInternetLayout.newInstance] factory method to
 * create an instance of this fragment.
 */
class PasInternetLayout : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var internetLayout: FrameLayout
    private lateinit var noInternetLayout: FrameLayout
    private lateinit var tryAgainButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vue = inflater.inflate(R.layout.fragment_pas_internet_layout, container, false)
        return vue
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize the layouts and button
        internetLayout = view.findViewById(R.id.internetLayout)
        noInternetLayout = view.findViewById(R.id.noInternetLayout)
        tryAgainButton = view.findViewById(R.id.try_again_button)

        // Check and draw layout based on network availability
        drawLayout()

        // Set OnClickListener for the Try Again button
        tryAgainButton.setOnClickListener {
            drawLayout()
        }
    }
    private fun isNetworkAvailable(): Boolean {
        val cm = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
    private fun drawLayout() {
        if (isNetworkAvailable()) {
            internetLayout.visibility = View.VISIBLE
            noInternetLayout.visibility = View.GONE


            findNavController().popBackStack()
        } else {
            noInternetLayout.visibility = View.VISIBLE
            internetLayout.visibility = View.GONE
        }
    }

}