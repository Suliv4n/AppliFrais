import java.sql.SQLException;

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
	}
	
	/**
	 * Connecte un comptable pour utiliser l'application.
	 */
	public static void connecter()
	{
		//FenetreConnexion con = new FenetreConnexion(, )
	}
	
}
