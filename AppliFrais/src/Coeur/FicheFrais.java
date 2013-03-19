package Coeur;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Passerelle.Passerelle;

public class FicheFrais 
{
	private String mois;
	private String idVisiteur;
	private int nbJustificatifs;
	private double montantValide;
	private Date dateModif;
	private String idEtat;
	private String libelleEtat;



	private ArrayList<LigneFraisForfait> lignesFraisForfait;
	private ArrayList<LigneFraisHorsForfait> lignesFraisHorsForfait;
	
	
	/**
	 * Constructeur de FicheFrais.
	 * 
	 * @param mois
	 * 		Mois correspondant à la fiche.
	 * @param id
	 * 		Id du visiteur dont la fiche appartient.
	 * @param nbJustifs
	 * 		Nombre de justificatifs.
	 * @param montantValide
	 * 		Ca je ne sais pas trop ce que c'est.
	 * @param dateModif
	 * 		Date de dernière modification de la fiche.
	 * @param idEtat
	 * 		Identifiant de l'état de la fiche.
	 * @param libEtat
	 * 		Libellé de l'état de la fiche.
	 * @param lignesFraisHorsForfait
	 * 		ArrayList de LigneFraisForfait associées à la fiche.
	 * @param lignesFraisForfait
	 * 		ArrayList de LigneFraisHorforfait associées à la fiche.
	 */
	public FicheFrais(String mois, String id, int nbJustifs,
			double montantValide, Date dateModif, String idEtat,
			String libEtat,
			ArrayList<LigneFraisHorsForfait> lignesFraisHorsForfait,
			ArrayList<LigneFraisForfait> lignesFraisForfait) 
	{
		this.mois = mois;
		this.idVisiteur = id;
		this.nbJustificatifs = nbJustifs;
		this.montantValide = montantValide;
		this.dateModif = dateModif;
		this.idEtat = idEtat;
		this.libelleEtat = libEtat;
		
		this.lignesFraisForfait = lignesFraisForfait;
		this.lignesFraisHorsForfait = lignesFraisHorsForfait;
	}




	//getters et setters
	
	public String getIdEtat() 
	{
		return idEtat;
	}
	
	public void setLibelleEtat(String libelleEtat) 
	{
		this.libelleEtat = libelleEtat;
	}
	
	public String getLibelleEtat() {
		return libelleEtat;
	}

	public void setIdEtat(String idEtat) {
		this.idEtat = idEtat;
	}
	
	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public String getIdVisiteur() {
		return idVisiteur;
	}

	public void setIdVisiteur(String idVisiteur) {
		this.idVisiteur = idVisiteur;
	}

	public int getNbJustificatifs() {
		return nbJustificatifs;
	}

	public void setNbJustificatifs(int nbJustificatifs) {
		this.nbJustificatifs = nbJustificatifs;
	}

	public double getMontantValide() {
		return montantValide;
	}

	public void setMontantValide(double montantValide) {
		this.montantValide = montantValide;
	}

	public Date getDateModif() {
		return dateModif;
	}

	public void setDateModif(Date dateModif) {
		this.dateModif = dateModif;
	}
	
	public ArrayList<LigneFraisForfait> getLignesFraisForfait() {
		return lignesFraisForfait;
	}

	public ArrayList<LigneFraisHorsForfait> getLignesFraisHorsForfait() {
		return lignesFraisHorsForfait;
	}
	
//      _____________________________________
	

	
	public void valider() throws SQLException
	{
		Passerelle.validerFicheFrais(this);
	}











	
}
