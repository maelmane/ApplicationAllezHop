    package crosemont.dti.g55.applicationallezhop.PageProfil

    import crosemont.dti.g55.applicationallezhop.Modèle.Trajet

    interface IPrésentateurProfil {

        val nbItems: Int

        fun getTrajetsVenirData(): List<Trajet>

        fun getTrajetsAnciensData(): List<Trajet>
        fun addReservedTrajet(trajet: Trajet)

    }