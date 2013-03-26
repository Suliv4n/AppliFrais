package Coeur;
import java.awt.Color;
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


	public String toString()
	{
		return "Fiche de : "+formatMois();		
	}

	private String formatMois()
	{
		String annee = mois.substring(0,4);
		String libMois = "";
		
		int numMois = Integer.parseInt(mois.substring(4,6));
		switch(numMois)
		{
		case(1):
			libMois = "Janvier";
			break;
		case(2):
			libMois = "Février";
			break;
		case(3):
			libMois = "Mars";
			break;
		case(4):
			libMois = "Avril";
			break;
		case(5):
			libMois = "Mai";
			break;
		case(6):
			libMois = "Juin";
			break;
		case(7):
			libMois = "Juillet";
			break;
		case(8):
			libMois = "Août";
			break;
		case(9):
			libMois = "Septembre";
			break;
		case(10):
			libMois = "Octobre";
			break;
		case(11):
			libMois = "Novembre";
			break;
		case(12):
			libMois = "Décembre";
			break;
		}
		
		return libMois + " " + annee; 
	}
	
	
	public Color getColor()
	{
		if(idEtat.equals("CL"))
		{
			return Color.DARK_GRAY; //gris
		}
		else if(idEtat.equals("CR"))
		{
			return Color.orange;  //orange
		}
		else if(idEtat.equals("RB"))
		{
			return new Color(0,100,0); //Vert
		}
		else if(idEtat.equals("VA"))
		{
			return Color.blue; //bleu
		}
		else
		{
			return Color.black;  //noir
		}
		
	}
}
