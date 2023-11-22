    package crosemont.dti.g55.applicationallezhop.PageProfil

    import crosemont.dti.g55.applicationallezhop.Modèle.Trajet

    interface IPrésentateurProfil {
        fun requêteSupprimerTrajetProfil(position: Int)

        fun rafraîchirAffichage()

        fun getItemString(position: Int): String?

        fun getConducteurString(position: Int): String?

        fun getAdresseString(position: Int): String?

        fun getDateString(position: Int): String?

        val nbItems: Int

        fun getTrajetsVenirData(): List<Trajet>

        fun getTrajetsAnciensData(): List<Trajet>
        fun addReservedTrajet(trajet: Trajet)

    }