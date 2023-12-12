    package crosemont.dti.g55.applicationallezhop.PageProfil

    import crosemont.dti.g55.applicationallezhop.Modèle.Trajet



    interface IPrésentateurProfil {

        val nbItems: Int

        suspend fun getTrajetsVenirData(): List<Trajet>

        suspend fun getTrajetsAnciensData(): List<Trajet>
        suspend fun addReservedTrajet(trajet: Trajet)


        fun saveFavoriteAddress(address: String)
        fun removeFavoriteAddress(address: String)

    }