package Launcher;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Coeur.FicheFrais;
import Coeur.Visiteur;
import GUI.Fenetre;
import GUI.FenetreConnexion;




public class Launcher 
{	
	private static boolean connecte = false;
	
	public static Fenetre fenetre;
	
	public static void main(String[] args)
	{
		//connecter();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Erreur driver non chargé : "+e);
		}
		
		fenetre = new Fenetre();
		fenetre.setVisible(true);
		
		connecter();
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
		//JOptionPane jop = new JOptionPane();
		//jop.showMessageDialog(null,"Connection = " + connecte,"test",JOptionPane.PLAIN_MESSAGE);
	}
	
}
