package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.Village.VillageSansChefException;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		Etal etal2 = new Etal();
		Gaulois gaulois = new Gaulois("NomGaulois", 10);
		etal.occuperEtal(gaulois, "fleurs", 3);
		try {
			etal.acheterProduit(0, gaulois);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		try {
			etal2.acheterProduit(3, gaulois);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		etal.libererEtal();
		Village village= new Village("VillageCasSansChef",4,4);
		try {
			village.afficherVillageois();
		} catch (VillageSansChefException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test");
	}
}
