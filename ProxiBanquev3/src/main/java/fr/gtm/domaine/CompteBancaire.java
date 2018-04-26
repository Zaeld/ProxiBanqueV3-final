package fr.gtm.domaine;

/**
 * Compte Bancaire est une classe abstraite dont vont heriter les classes
 * Courant et Epargne
 * 
 * @author Stagiaire
 *
 */
public abstract class CompteBancaire {

	// Declaration des attributs caracterisant un objet heritant de la classe
	// CompteBancaire
	private int idCompte;
	private int numCompte;
	private double solde;
	private int idClient;
	private String typeDeCompte;

	// Constructeur par defaut
	public CompteBancaire() {
		this.numCompte = (int) (Math.random() * 10000);
		this.solde = 12;
		this.idCompte = 1;
		this.idClient = 1;
	}

	public int getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(int numCompte) {
		this.numCompte = numCompte;
	}

	public String getTypeDeCompte() {
		return typeDeCompte;
	}

	public void setTypeDeCompte(String typeDeCompte) {
		this.typeDeCompte = typeDeCompte;
	}

	// Getters et Setters
	public int getNumeroCompte() {
		return numCompte;
	}

	public void setNumeroCompte(int numeroCompte) {
		this.numCompte = numeroCompte;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	
	/* Redefinition de la methode toString()
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Compte " + this.getTypeDeCompte() + " numéro "+this.getNumeroCompte()+" ayant pour solde " + this.getSolde() + " EUR.";
	}
}
