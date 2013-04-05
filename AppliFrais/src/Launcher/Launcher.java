package Launcher;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Coeur.FicheFrais;
import Coeur.Visiteur;
import GUI.Fenetre;
import GUI.FenetreConnexion;
import Passerelle.Passerelle;




public class Launcher 
{	
	private static boolean connecte = false;
	
	public static Fenetre fenetre;
	
	public static ArrayList<Visiteur>lesVisiteurs; //OOKKKKAAAAAYYY!
	
	public static void main(String[] args)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Erreur driver non chargé : "+e);
		}
		
		try 
		{
			lesVisiteurs = Passerelle.getLesVisiteurs();
			fenetre = new Fenetre();
			fenetre.setConnecte(false);
			fenetre.setVisible(true);
			connecter();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Connecte un comptable pour utiliser l'application.
	 */
	public static void connecter()
	{
		FenetreConnexion con = new FenetreConnexion();
		con.setVisible(true);
	}
	
	public static void estConnecte(boolean b)
	{
		connecte = b;
		fenetre.setConnecte(b);
		//JOptionPane jop = new JOptionPane();
		//jop.showMessageDialog(null,"Connection = " + connecte,"test",JOptionPane.PLAIN_MESSAGE);
	}
	
}
