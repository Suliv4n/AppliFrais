package Coeur;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import Passerelle.Passerelle;

public class LigneFraisHorsForfait {

	private int id;
	private Date date; 
	private double montant;
	private String libelle; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	

	public LigneFraisHorsForfait(int id, Date date, double montant, String libelle){
		this.id = id;
		this.date = date;
		this.montant = montant;
		this.libelle = libelle;
	}
	
	public void refusser() throws SQLException
	{
		Passerelle.refusserHorsForfait(id);
	}
	
	@Override
	public String toString() {
		return "LigneFraisHorsForfait [id=" + id + ", date=" + date
				+ ", montant=" + montant + ", libelle=" + libelle + "]";
	}
	

	
}
