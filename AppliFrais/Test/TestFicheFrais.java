import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;


import org.junit.Before;
import org.junit.Test;

import Coeur.FicheFrais;
import Coeur.LigneFraisForfait;
import Coeur.LigneFraisHorsForfait;


public class TestFicheFrais {

	private FicheFrais f;
	private Date date;
	private ArrayList<LigneFraisHorsForfait> lfhf;
	private ArrayList<LigneFraisForfait> lff;
	
	@Before
	public void setUp()
	{
		lff = new ArrayList<LigneFraisForfait>();
		lfhf = new ArrayList<LigneFraisHorsForfait>();
		

		Date date = new Date();
		
		
		f = new FicheFrais("201003", "a", 3, 10, date, "TE", "test", lfhf, lff);
	}
	@Test
	public void testFicheFrais() 
	{
		assertNotNull("Instance non créée.",f);
		assertTrue("id n'a pas été correctement initialisé.","a".equals(f.getIdVisiteur()));
		assertTrue("idEtat n'a pas été correctement initialisé.","TE".equals(f.getIdEtat()));
		assertTrue("libEtat n'a pas été correctement initialisé.","test".equals(f.getLibelleEtat()));
		assertEquals("NbJustificatifs n'a pas été correctement initialisé.",3,f.getNbJustificatifs());
		assertTrue("NbJustificatifs n'a pas été correctement initialisé.",10==f.getMontantValide());
		assertTrue("dateModif n'a pas été correctement initialisée",date.equals(f.getDateModif()));
		
		assertTrue("ligneFraisForfait n'a pas été correctement initialisée",lff.equals(f.getLignesFraisForfait()));
		assertTrue("ligneFraisHorsForfait n'a pas été correctement initialisée",lfhf.equals(f.getLignesFraisHorsForfait()));
	}

	
	@Test
	public void testValider() 
	{
		//fail("Not yet implemented");
	}
	
}
