package crosemont.dti.g55.applicationallezhop.PageProfil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.R

class AdresseAdapter(private var data: List<String>,private val presentateurProfil: PrésentateurProfil) : RecyclerView.Adapter<AdresseAdapter.AdresseFavorisViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdresseFavorisViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_adresse, parent, false)
        return AdresseFavorisViewHolder(view, presentateurProfil)
    }

    override fun onBindViewHolder(holder: AdresseFavorisViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class AdresseFavorisViewHolder(
        itemView: View,
        private val presentateurProfil: PrésentateurProfil
    ) : RecyclerView.ViewHolder(itemView) {
        private val favoriteAddressTextView: TextView =
            itemView.findViewById(R.id.tvfavoriteAddressTextView)

        fun bind(address: String) {
            favoriteAddressTextView.text = address
        }

        fun bindAdresse(trajet: Trajet) {
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
