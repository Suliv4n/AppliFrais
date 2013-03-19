import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import Coeur.Visiteur;


public class TestVisiteur
{

	
	@Test
	public void testVisiteur() 
	{
		try 
		{
			Visiteur v = new Visiteur("Test");
		} 
		
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fail("Not yet implemented");
	}

}
