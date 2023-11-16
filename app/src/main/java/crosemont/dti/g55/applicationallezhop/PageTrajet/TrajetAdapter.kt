package crosemont.dti.g55.applicationallezhop.PageTrajet

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.R

class TrajetAdapter(val _présentateur: IPrésentateurTrajet?) : RecyclerView.Adapter<TrajetAdapter.TrajetViewHolder>() {

    class TrajetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val conducteurTextView: TextView = view.findViewById(R.id.tvConducteurItem)
        private val heureArriveeTextView: TextView = view.findViewById(R.id.tvHeureArriverItem)

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
        val trajet = _présentateur!!.getTrajetsVenirData()[position]
        holder.bind(trajet)
        val btnReserver: Button = holder.itemView.findViewById(R.id.btnConfirmationItem)
        btnReserver.setOnClickListener {
            Log.d("Réservation", "réserver $position")
            _présentateur.effectuerRéservation(position)
        }
    }

    override fun getItemCount():Int  = _présentateur!!.nbItems
}


