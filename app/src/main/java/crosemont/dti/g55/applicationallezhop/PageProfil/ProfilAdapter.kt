package crosemont.dti.g55.applicationallezhop.PageProfil

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProfilAdapter(private var data: List<Trajet>,private val presentateurProfil: PrésentateurProfil) : RecyclerView.Adapter<ProfilAdapter.ProfilViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trajet, parent, false)
        return ProfilViewHolder(view, presentateurProfil)
    }

    override fun onBindViewHolder(holder: ProfilViewHolder, position: Int) {
        val trajet = data[position]
        CoroutineScope(Dispatchers.Main).launch { holder.bind(trajet) }
    }

    fun setData(newData: List<Trajet>) {
        val updatedData = data.toMutableList()
        updatedData.addAll(newData)
        data = updatedData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ProfilViewHolder(itemView: View, private val presentateurProfil: PrésentateurProfil) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.tvConducteurItem)
        private val destinationTextView: TextView = itemView.findViewById(R.id.tvHeureArriverItem)
        private val conducteurTextView: TextView = itemView.findViewById(R.id.conducteurTextView)

        fun bind(trajet: Trajet) {
            dateTextView.text = trajet.date ?: "N/A"
            destinationTextView.text = trajet.destination.toString() ?: "N/A"
            conducteurTextView.text = trajet.conducteur ?: "N/A"
            val btnFavori: ImageButton = itemView.findViewById(R.id.btnFavori)


            if (trajet.estFavori) {
                btnFavori.setImageResource(android.R.drawable.btn_star_big_on)
            } else {
                btnFavori.setImageResource(android.R.drawable.btn_star_big_off)
            }

            btnFavori.setOnClickListener {
                trajet.estFavori = !trajet.estFavori

                // UI
                if (trajet.estFavori) {
                    btnFavori.setImageResource(android.R.drawable.btn_star_big_on)
                    presentateurProfil.saveFavoriteAddress(trajet.destination.toString())
                } else {
                    btnFavori.setImageResource(android.R.drawable.btn_star_big_off)
                    presentateurProfil.removeFavoriteAddress(trajet.destination.toString())
                }


            }
        }
    }
}
