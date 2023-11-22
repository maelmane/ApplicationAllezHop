package crosemont.dti.g55.applicationallezhop.PageProfil

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
        println("Binding position: $position, Data: $trajet")

        holder.bind(trajet)

    }
    fun setData(newData: List<Trajet>) {
        data = newData
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = data.size

    class ProfilViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.tvConducteurItem)
        private val destinationTextView: TextView = itemView.findViewById(R.id.tvHeureArriverItem)
        private val conducteurTextView: TextView = itemView.findViewById(R.id.conducteurTextView)

        fun bind(trajet: Trajet) {

            println("Date: ${trajet.date}, Destination: ${trajet.destination}, Conducteur: ${trajet.conducteur}")

            dateTextView.text = trajet.date ?: "N/A"
            destinationTextView.text = trajet.destination ?: "N/A" // If destination is null, display "N/A"
            conducteurTextView.text = trajet.conducteur ?: "N/A" // If conducteur is null, display "N/A"
        }
    }


    fun addTrajet(trajet: Trajet) {
        val newList = data.toMutableList()
        newList.add(trajet)
        data = newList
        notifyItemInserted(newList.size - 1)
    }

}
