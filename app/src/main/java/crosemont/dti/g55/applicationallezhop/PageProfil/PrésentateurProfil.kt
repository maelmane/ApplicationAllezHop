package crosemont.dti.g55.applicationallezhop.PageProfil

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import crosemont.dti.g55.applicationallezhop.Modèle.ModèleProfil
import crosemont.dti.g55.applicationallezhop.Modèle.ModèleTrajet
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.PageTrajet.TrajetAdapter
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon

class PrésentateurProfil(var vue: vue_profil): IPrésentateurProfil {

    var modèle= ModèleProfil(SourceBidon.getInstance())
    private var _adapter: ProfilAdapter = ProfilAdapter(emptyList(), this)
    override suspend fun getTrajetsVenirData(): List<Trajet> {
        return modèle.chargerTrajetsÀVenir()
    }

    override suspend fun getTrajetsAnciensData(): List<Trajet> {
        return  modèle.chargerTrajetsAnciens()
    }

    override suspend fun addReservedTrajet(trajet: Trajet) {
        modèle.sourceDeDonnées.créer(trajet)
        vue.rafraîchirAffichage()

    }


    override val nbItems: Int
        get() = modèle.tailleTrajetsÀVenir
    override fun saveFavoriteAddress(address: String) {
        // Implement the logic to save the favorite address
        // For example, you can save it in SharedPreferences
        val sharedPreferences =
            vue.requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Retrieve existing favorite addresses
        val savedFavoriteAddressesJson =
            sharedPreferences.getString("favoriteAddresses", null)
        val savedFavoriteAddresses = if (savedFavoriteAddressesJson != null) {
            Gson().fromJson<List<String>>(savedFavoriteAddressesJson, object : TypeToken<List<String>>() {}.type).toMutableList()
        } else {
            mutableListOf()
        }



        // Add the new address
        savedFavoriteAddresses.add(address)

        // Convert to JSON and save
        val addressesJson = Gson().toJson(savedFavoriteAddresses)
        editor.putString("favoriteAddresses", addressesJson)
        editor.apply()
    }

    override fun removeFavoriteAddress(address: String) {
        // Implement the logic to remove the favorite address
        // For example, you can remove it from SharedPreferences
        val sharedPreferences =
            vue.requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Retrieve existing favorite addresses
        val savedFavoriteAddressesJson =
            sharedPreferences.getString("favoriteAddresses", null)
        val savedFavoriteAddresses = if (savedFavoriteAddressesJson != null) {
            Gson().fromJson<List<String>>(savedFavoriteAddressesJson, object : TypeToken<List<String>>() {}.type).toMutableList()
        } else {
            mutableListOf()
        }


        // Remove the specified address
        savedFavoriteAddresses.remove(address)

        // Convert to JSON and save
        val addressesJson = Gson().toJson(savedFavoriteAddresses)
        editor.putString("favoriteAddresses", addressesJson)
        editor.apply()
    }
}