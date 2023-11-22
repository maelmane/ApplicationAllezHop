package crosemont.dti.g55.applicationallezhop.PageProfil

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.R

class ProfilAdapter(private var data: List<Trajet>) : RecyclerView.Adapter<ProfilAdapter.ProfilViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trajet, parent, false)
        return ProfilViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfilViewHolder, position: Int) {
        val trajet = data[position]
        Log.d("ProfilAdapter", "Binding position: $position, Data: $trajet")
        holder.bind(trajet)
    }

    fun setData(newData: List<Trajet>) {
        data = newData.toMutableList()
        Log.d("ProfilAdapter", "setData called with new data: $newData")
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ProfilViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.tvConducteurItem)
        private val destinationTextView: TextView = itemView.findViewById(R.id.tvHeureArriverItem)
        private val conducteurTextView: TextView = itemView.findViewById(R.id.conducteurTextView)

        fun bind(trajet: Trajet) {
            Log.d("ProfilAdapter", "Date: ${trajet.date}, Destination: ${trajet.destination}, Conducteur: ${trajet.conducteur}")

            dateTextView.text = trajet.date ?: "N/A"
            destinationTextView.text = trajet.destination ?: "N/A"
            conducteurTextView.text = trajet.conducteur ?: "N/A"
        }
    }
}
