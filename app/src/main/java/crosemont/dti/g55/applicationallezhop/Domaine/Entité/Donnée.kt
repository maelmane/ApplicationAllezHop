package crosemont.dti.g55.applicationallezhop.Domaine.Entité

import crosemont.dti.g55.applicationallezhop.Modèle.Trajet

class Donnée (
	var trajet : Array<Trajet> = emptyArray<Trajet>(),
	var description : String = "",
	var serveur : Serveur? = null
) {

}
	
