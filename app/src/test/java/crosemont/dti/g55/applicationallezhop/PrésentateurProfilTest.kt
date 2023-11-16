package crosemont.dti.g55.applicationallezhop



import crosemont.dti.g55.applicationallezhop.Modèle.ModèleProfil
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceBidon
import crosemont.dti.g55.applicationallezhop.sourceDeDonnées.SourceDeDonnées
import crosemont.dti.g55.applicationallezhop.Modèle.Trajet
import crosemont.dti.g55.applicationallezhop.Modèle.Voiture
import crosemont.dti.g55.applicationallezhop.PageProfil.PrésentateurProfil
import crosemont.dti.g55.applicationallezhop.PageProfil.vue_profil
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
@RunWith(MockitoJUnitRunner::class)
class PrésentateurProfilTest {

    private lateinit var présentateur: PrésentateurProfil
    private lateinit var source: SourceDeDonnées
    private lateinit var vue: vue_profil

    @Before
    fun setUp() {
        source = Mockito.mock(SourceDeDonnées::class.java) // Utilisez l'interface SourceDeDonnées
        vue = Mockito.mock(vue_profil::class.java)
        présentateur = PrésentateurProfil(vue)
        présentateur.modèle = ModèleProfil(source)
    }

    @Test
    fun `lorsqu'on demande les trajets anciens, on obtient la liste correcte`() {
        val trajetsAnciensAttendus = listOf(
            Trajet("01/01/2023", "6400 16e Avenue,...", "Alice", "8:00", "7:00", Voiture(null)),
            Trajet("02/01/2023", "6400 16e Avenue,...", "Bob", "9:00", "8:00", Voiture(null))
        )
        Mockito.`when`(source.getTrajetsAnciensData()).thenReturn(trajetsAnciensAttendus)

        val trajets = présentateur.getTrajetsAnciensData()

        // Modifier intentionnellement l'assertion pour qu'elle échoue
        assertNotEquals(trajetsAnciensAttendus.size, trajets.size)
    }


    @Test
    fun `lorsqu'on demande les trajets à venir, on obtient la liste correcte`() {
        val trajetsÀVenirAttendus = listOf(
            Trajet("10/01/2023", "6400 16e Avenue,...", "Charlie", "10:00", "9:00", Voiture(null)),
            Trajet("11/01/2023", "6400 16e Avenue,...", "Dana", "11:00", "10:00", Voiture(null))
        )
        Mockito.`when`(source.getTrajetsVenirData()).thenReturn(trajetsÀVenirAttendus)

        val trajets = présentateur.getTrajetsVenirData()

        // Modifier intentionnellement l'assertion pour qu'elle échoue
        assertNotEquals(trajetsÀVenirAttendus, trajets)
    }

}
