package GUI;


import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import Coeur.FicheFrais;
import Coeur.LigneFraisForfait;
import Coeur.Visiteur;
import Launcher.Launcher;
import Passerelle.*;
/*
         /;;;;\  ___      .;;.
        |;(;;;-""   `'-.,;;;;;\
         \;'            ';;;);/
         /                \;;'
        /    .;.   .;.     \
        |   ;;o;; ;;o;;    |
        ;   '"-'` `'-"'    |
        /\      ._.       /
      ;;;;;_   ,_Y_,   _.'
     /;;;;;\`--.___.--;.
    /|;;;;;;;.__.;;;.  \\
   ;  \;;;;;;;;;;;;;;\  ;\__  .;.
   |   ';;;;;;;;=;;;;'  |-__;;;;/
   |     `""`  .---._  /;/;;\;;/
  / ;         /;;;;;;;-;/;;/|;/
  \_,\       |;;;;;;;;;;;;| |
      '-...--';;;;;;;;;;;;\/
                `"""`   `"`
 */

/**
 * Ma jolie fenêtre.
 * 
 * 
 * @author gsh
 *
 */
public class Fenetre extends JFrame implements ActionListener
{
    /**//**//**//**//**//**//**//**//**//**//**/
   /**/  private FicheFrais current_fiche; /**/
  /**//**//**//**//**//**//**//**//**//**//**/
	
    //
	private JMenuBar menu;
	
    private JMenu connexion;
    private JMenuItem se_connecter;
    private JMenuItem se_deconnecter;
    //
    
    //
    private JComboBox liste_fichesFrais;
    private JComboBox liste_visiteurs;
    private JButton selectionnerFiche;
    //
   
    
    //Pseudo GroupBox
    private JPanel lignesFraisForfaitsPanel;
    private JPanel selectionFichePanel;
    //
    
    public Fenetre() throws SQLException
    {
    	
        super("Fenetre");
        
        //Init pseudo group box
        lignesFraisForfaitsPanel = new JPanel();
        lignesFraisForfaitsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Lignes frais forfait"));
        lignesFraisForfaitsPanel.setLayout(new GridLayout());
        
        selectionFichePanel = new JPanel();
        selectionFichePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Selectionner une fiche"));
        selectionFichePanel.setLayout(new GridLayout(3,2));//
        //----------------------------------
        
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
        setLayout(new GridLayout(3,0));
        
        //--------------LISTE VISITEURS----------------------------------
        JPanel p1 = new JPanel();
        JLabel lab_listeVisiteurs = new JLabel(" Visiteurs");        
        liste_visiteurs = new JComboBox(Launcher.lesVisiteurs.toArray());
        p1.add(lab_listeVisiteurs);
        
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        p1.add(lab_listeVisiteurs);
        p1.add(liste_visiteurs);
        liste_visiteurs.addActionListener(this);
        selectionFichePanel.add(p1);
        //---------------------------------------------------------------
        
        //--------------LISTE FICHES FRAIS (avec des couleurs!)----------
        JPanel p2 = new JPanel();
        JLabel lab_listeFicheFrais = new JLabel("Fiches Frais");
        liste_fichesFrais = new JComboBox(((Visiteur)liste_visiteurs.getSelectedItem()).getFiches().toArray());
       
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        p2.add(lab_listeFicheFrais);      
        
        
        
        
        selectionnerFiche = new JButton("Sélectionner");
  
        add(selectionFichePanel);
        
        	//Ajouter couleurs dans Liste fiches frais
        liste_fichesFrais.setRenderer(new CouleurComboBox());
        
        p2.add(liste_fichesFrais);  
        selectionFichePanel.add(p2);
        //---------------------------------------------------------------
        
        
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        p3.add(selectionnerFiche);
        selectionnerFiche.addActionListener(this);
        selectionFichePanel.add(p3);
        
        //-------------------LIGNE FRAIS FORFAIT-------------------------
        //préparation du panel
        
        
        add(lignesFraisForfaitsPanel);
        lignesFraisForfaitsPanel.setVisible(false);
        
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
				liste_fichesFrais.removeAllItems();
				
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
		//Bouton sélectionner (fiche)
		else if(e.getSource().equals(selectionnerFiche))
		{
			setFiche((FicheFrais)liste_fichesFrais.getSelectedItem());
		}
	}
	
	/**
	 * Met à jour les composants de la fenêtre à la sélection d'une fiche.
	 * 
	 * @param ficheFrais
	 */
	private void setFiche(FicheFrais ficheFrais) 
	{
		lignesFraisForfaitsPanel.setVisible(true);
		lignesFraisForfaitsPanel.removeAll();
		//((GridLayout)(lignesFraisForfaitsPanel.getLayout())).setColumns(1);	
		//((GridLayout)(lignesFraisForfaitsPanel.getLayout())).setRows(0);		
		//lignesFraisForfaitsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		lignesFraisForfaitsPanel.setLayout(new GridLayout(ficheFrais.getLignesFraisForfait().size(),2));
		
		int nbLignes = 1;
		JPanel[] sousPanels = new JPanel[ficheFrais.getLignesFraisForfait().size()];
		for(LigneFraisForfait lff : ficheFrais.getLignesFraisForfait())
		{
			//new JDialog(this,lff.getLibelle()).setVisible(true);
			sousPanels[nbLignes - 1] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel libFrais = new JLabel(lff.getLibelle());
			sousPanels[nbLignes - 1].add(libFrais);
			lignesFraisForfaitsPanel.add(sousPanels[nbLignes - 1]);
			nbLignes++;
		}
		
		lignesFraisForfaitsPanel.getComponent(2);
	}

	public void setConnecte(boolean connecte)
	{
		se_deconnecter.setEnabled(connecte);
		se_connecter.setEnabled(!connecte);
	}

}
