package crosemont.dti.g55.applicationallezhop.PageTrajet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.R

class TrajetAdapter(private val trajets: List<Trajet>) : RecyclerView.Adapter<TrajetAdapter.TrajetViewHolder>() {

    class TrajetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val conducteurTextView: TextView = view.findViewById(R.id.tvConducteurItem)
        private val heureArriveeTextView: TextView = view.findViewById(R.id.tvHeureArriverItem)
        private val btnReserver: Button = view.findViewById(R.id.btnConfirmationItem)

        fun bind(trajet: Trajet) {
            conducteurTextView.text = trajet.conducteur
            heureArriveeTextView.text = trajet.heureArriver

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrajetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trajet_dans_trajet, parent, false)
        return TrajetViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrajetViewHolder, position: Int) {
        holder.bind(trajets[position])
    }

    override fun getItemCount():Int  = trajets.size
}


