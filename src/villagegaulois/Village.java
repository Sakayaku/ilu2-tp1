package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		// marche = new Marche[nbEtals];
	}

	public class Marche {
		private Etal etals[];

		private Marche(int nbEtalsMarche) {
			etals = new Etal[nbEtalsMarche];
			for (int i = 0; i < nbEtalsMarche; i++) {
				etals[i] = new Etal();
			}
		}

		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		public int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() == false) {
					return i;
				}
			}
			return -1;
		}

		public Etal[] trouverEtals(String produit) {
			Etal etalsProduit[];
			int nbEtalsavecProduit = 0;
			for (int c = 0; c < etals.length; c++) {
				if (etals[c].contientProduit(produit)) {
					nbEtalsavecProduit += 1;
				}
			}
			etalsProduit = new Etal[nbEtalsavecProduit];
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					for (int j = 0; j < etalsProduit.length; j++) {
						if (!etalsProduit[j].isEtalOccupe() && etals[i].isEtalOccupe()) {
							etalsProduit[j] = etals[i];
						}
					}
				}
			}
			return etalsProduit;
		}

		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}

		public String afficherMarche() {
			int nbEtalVide = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() == false) {
					nbEtalVide += 1;
				} else {
					etals[i].afficherEtal();
				}
			}
			return "Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n";
		}
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}