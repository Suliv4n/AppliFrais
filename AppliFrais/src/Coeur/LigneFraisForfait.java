package Coeur;

public class LigneFraisForfait {

	private String idFraisForfait, libelle;
	private int quantite;
	private double montant;
	

	public LigneFraisForfait(String unIdFraisForfait, String unLibelle, int uneQuantite, double unMontant) {
		idFraisForfait = unIdFraisForfait;
		libelle = unLibelle;
		quantite = uneQuantite;
		montant = unMontant;	
	}
	
	public String getIdFraisForfait() {
		return idFraisForfait;
	}
	public void setIdFraisForfait(String idFraisForfait) {
		this.idFraisForfait = idFraisForfait;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	public String toString() {
		return "LigneFraisForfait [idFraisForfait=" + idFraisForfait
				+ ", libelle=" + libelle + ", quantite=" + quantite
				+ ", montant=" + montant + "]";
	}
	
	
	
}
