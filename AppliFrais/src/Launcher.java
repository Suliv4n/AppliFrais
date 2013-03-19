import java.sql.SQLException;

import Coeur.FicheFrais;
import Coeur.Visiteur;
import GUI.Fenetre;




public class Launcher 
{	
	static private boolean connecte = false;
	
	
	
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
		
		Fenetre fenetre = new Fenetre();
		fenetre.setVisible(true);
	}
	
	public static void connecter()
	{

	}
	
}
