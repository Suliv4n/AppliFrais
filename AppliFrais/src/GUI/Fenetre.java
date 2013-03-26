package GUI;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import Coeur.Visiteur;
import Launcher.Launcher;
import Passerelle.*;


/**
 * 
 * @author gsh
 *
 */
public class Fenetre extends JFrame implements ActionListener
{
    
    //
	private JMenuBar menu;
	
    private JMenu connexion;
    private JMenuItem se_connecter;
    private JMenuItem se_deconnecter;
    //
    
    //
    private JComboBox liste_fichesFrais;
    private JComboBox liste_visiteurs;
    //
   
    public Fenetre() throws SQLException
    {
        super("Fenetre");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 500);
        centrer();
        
        
        //MENU------------------------------
        menu = new JMenuBar();
        
        connexion = new JMenu("Connexion");
        se_connecter = new JMenuItem("Se connecter");
        se_connecter.addActionListener(this);
        se_deconnecter = new JMenuItem("Se déconnecter");
        se_deconnecter.addActionListener(this);
        se_deconnecter.setEnabled(false);
        connexion.add(se_connecter);
        connexion.add(se_deconnecter);
        
        menu.add(connexion);
        
        setJMenuBar(menu);
        //FIN MENU--------------------------
        
        //Placement des éléments
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        //--------------LISTE VISITEURS----------------------------------
        JPanel p1 = new JPanel();
        JLabel lab_listeVisiteurs = new JLabel(" Visiteurs");        
        liste_visiteurs = new JComboBox(Launcher.lesVisiteurs.toArray());
        p1.add(lab_listeVisiteurs);
        
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        p1.add(lab_listeVisiteurs);
        p1.add(liste_visiteurs);
        liste_visiteurs.addActionListener(this);
        
        add(p1);
        //---------------------------------------------------------------
        
        //--------------LISTE FICHES FRAIS-------------------------------
        JPanel p2 = new JPanel();
        JLabel lab_listeFicheFrais = new JLabel("Fiches Frais");
        //TODO vérifier que la liste de visiteurs contient plus d'un visiteur
        liste_fichesFrais = new JComboBox(((Visiteur)liste_visiteurs.getSelectedItem()).getFiches().toArray());
       
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        p2.add(lab_listeFicheFrais);
        p2.add(liste_fichesFrais);        
        
        add(p2);
        //---------------------------------------------------------------
    }
    
    private void centrer()
    {
    	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    	setLocation((screen.width - getSize().width)/2,(screen.height - getSize().height)/2); 
    }

	@Override
	public void actionPerformed(ActionEvent e)
	{
		//Connexion => se connecter
		if(e.getSource().equals(se_connecter))
		{
			Launcher.connecter();
		}
		//Connexion => se deconnecter
		else if(e.getSource().equals(se_deconnecter))
		{
			Launcher.estConnecte(false);
		}
		else if(e.getSource().equals(liste_visiteurs))
		{
			try 
			{
				liste_fichesFrais.removeAllItems(); //TO
				
				for(Object v : Passerelle.get_fiches_frais_visiteur(((Visiteur)liste_visiteurs.getSelectedItem()).getId()).toArray())
				{
					liste_fichesFrais.addItem(v);
				}
			
			} 
			catch (SQLException e1) 
			{
				//afficher message erreur (dialogue);
				e1.printStackTrace();
			}
		}
	}
	
	public void setConnecte(boolean connecte)
	{
		se_deconnecter.setEnabled(connecte);
		se_connecter.setEnabled(!connecte);
	}

}
