package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		// etal.libererEtal();
		Gaulois gaulois = new Gaulois("NomGaulois", 10);
		etal.acheterProduit(23, gaulois);
		System.out.println("Fin du test");
	}
}
