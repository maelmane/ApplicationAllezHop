package crosemont.dti.g55.applicationallezhop.PageTrajet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.PageProfil.ProfilAdapter
import crosemont.dti.g55.applicationallezhop.R

class TrajetAdapter(private val trajets: List<Trajet>) : RecyclerView.Adapter<TrajetAdapter.TrajetViewHolder>() {

    class TrajetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val conducteurTextView: TextView = view.findViewById(R.id.conducteurTextView)
        private val heureArriveeTextView: TextView = view.findViewById(R.id.tv_heure)
        private val btnReserver: Button = view.findViewById(R.id.btn_confirmer_reservation)

        fun bind(trajet: Trajet) {
            conducteurTextView.text = trajet.conducteur
            heureArriveeTextView.text = trajet.date
            // Configurez ici le clic sur le bouton Réserver si nécessaire
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrajetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trajet, parent, false)
        return TrajetViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrajetViewHolder, position: Int) {
        holder.bind(trajets[position])
    }

    override fun getItemCount() = trajets.size
}


