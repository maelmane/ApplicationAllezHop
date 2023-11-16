package crosemont.dti.g55.applicationallezhop

import crosemont.dti.g55.applicationallezhop.Modèle.ModèleTrajet
import crosemont.dti.g55.applicationallezhop.PageTrajet.PrésentateurTrajet
import crosemont.dti.g55.applicationallezhop.PageTrajet.vue_trajet
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceDeDonnées
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.Modèle.Voiture
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PrésentateurTrajetTest {

    private lateinit var présentateur: PrésentateurTrajet
    private lateinit var source: SourceDeDonnées
    private lateinit var vue: vue_trajet

    @Before
    fun setUp() {
        source = Mockito.mock(SourceDeDonnées::class.java) // Utilisation de SourceDeDonnées plutôt que SourceBidon
        vue = Mockito.mock(vue_trajet::class.java)
        présentateur = PrésentateurTrajet(vue)
        présentateur.modèle = ModèleTrajet(source)
    }

    @Test
    fun `lorsqu'on demande les trajets à venir, on obtient la liste correcte`() {
        val trajetsAttendus = listOf(
            Trajet("05/01/2023", "6400 16e Avenue,...", "Alice", "8:00", "7:00", Voiture(null)),
            Trajet("06/01/2023", "6400 16e Avenue,...", "Bob", "9:00", "8:00", Voiture(null))
        )
        Mockito.`when`(source.getTrajetsVenirData()).thenReturn(trajetsAttendus)

        val trajets = présentateur.getTrajetsVenirData()

        assertEquals(trajetsAttendus.size, trajets.size)
        assertEquals(trajetsAttendus, trajets) // Comparaison des listes entières pour plus de précision
    }
}
