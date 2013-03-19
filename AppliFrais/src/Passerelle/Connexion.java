package Passerelle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Connexion 
{
	private static Connection c;
	
	public static Connection getConnexion() throws SQLException
	{
		if(c == null)
		{
			c = DriverManager.getConnection("jdbc:mysql://localhost/gsb_frais","root","");
		}
		
		return c;
	}
}
