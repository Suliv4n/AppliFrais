package Coeur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import Passerelle.Passerelle;

public class Visiteur 
{
	private String id;
	private String nom;
	private String prenom;
	private ArrayList<FicheFrais> lesFichesFrais;

	
	/**
	 * Constructeur de Visiteur.
	 * 
	 * @param id
	 * 		Identifiant du visiteur.
	 * @throws SQLException
	 */
	public Visiteur(String id) throws SQLException
	{
		this.id = id;
		this.nom = Passerelle.getNomVisiteur(id);
		this.prenom = Passerelle.getPrenomVisiteur(id);
	
		lesFichesFrais = Passerelle.get_fiches_frais_visiteur(id);
		Collections.sort(lesFichesFrais);
	}
	
	/**
	 * Renvoie la fiche de frais correspondant au Visiteur et au mois passer
	 * en paramètre.
	 * 
	 * @param mois
	 * 		Mois de la fiche à retourner.
	 * @return
	 * 		La fiche de frais correspondant au Visiteur et au mois.
	 */
	public FicheFrais rechercher_fiche_frais(String mois)
	{
		for(FicheFrais f : lesFichesFrais)
		{
			if(f.getMois().equals(mois))
			{
				return f;
			}				
		}
		return null;
	}
	
	public ArrayList<FicheFrais> getFiches()
	{
		return lesFichesFrais;
	}
	
	public String toString()
	{
		return nom + " " + prenom;
	}

	public String getId() 
	{
		return id;
	}
}
