package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village  {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public class VillageSansChefException extends NullPointerException{
		private static final long serialVersionUID=1L;
		public VillageSansChefException() {
			
		}
		public VillageSansChefException(String message) {
			super(message);
		}
	}
	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}

	private static class Marche {
		private Etal[] etals;

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
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}

		public Etal[] trouverEtals(String produit) {
			Etal[] etalsProduit;
			int nbEtalsavecProduit = 0;
			for (int c = 0; c < etals.length; c++) {
				if (etals[c].isEtalOccupe() && etals[c].contientProduit(produit)) {
					nbEtalsavecProduit += 1;
				}
			}
			etalsProduit = new Etal[nbEtalsavecProduit];
			int j=0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit) && etalsProduit[j]==null) {
					etalsProduit[j] = etals[i];
					j+=1;
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
			String chaine="";
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					nbEtalVide += 1;
				}else {
					chaine+=etals[i].afficherEtal();
				}
			}
			if (nbEtalVide!=0) {
				return chaine+"Il reste " + nbEtalVide + " étals non utilisés dans le marché\n";
			}else {
				return chaine;
			}
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

	public String afficherVillageois() throws VillageSansChefException {
		if (this.chef==null) {
			throw new VillageSansChefException();
		}
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		int etalLibre=marche.trouverEtalLibre();
		chaine.append("Le vendeur "+vendeur.getNom()+" cherche un endroit pour vendre "+nbProduit+" "+produit+".\n");
		marche.utiliserEtal(etalLibre, vendeur, produit, nbProduit);
		chaine.append("Le vendeur "+vendeur.getNom()+" vend des "+produit+" à l'étal n°"+(etalLibre+1)+".\n");
		return chaine.toString();
	}
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Les vendeurs qui proposent des "+produit+" sont :\n");
		Etal[] etalsProduit= marche.trouverEtals(produit);
		for (int i=0;i<etalsProduit.length;i++) {
			chaine.append("- "+etalsProduit[i].getVendeur().getNom()+"\n");
		}
		return chaine.toString();
	}
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine = new StringBuilder();
		Etal etalVendeur=marche.trouverVendeur(vendeur);
		chaine.append(etalVendeur.libererEtal()+etalVendeur.getQuantiteDebutMarche()+" qu'il voulait vendre.");
		return chaine.toString();
	}
	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le marché du village "+"'"+nom+"'"+" possède plusieurs étals :\n");
		chaine.append(marche.afficherMarche());
		return chaine.toString();
	}
}