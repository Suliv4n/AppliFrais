package GUI;


import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.*;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

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
    private JPanel selectionFichePanel;
    private JPanel lignesFraisForfaitsPanel;
    private JPanel lignesHorsFraisForfaitPanel;
    //
    
    private JTable lignesHorsFraisForfaitTable;
    
    private HashMap<String, JTextField> qteLigneFraisForfaitTB;
    
    public Fenetre() throws SQLException
    {
    	
        super("Fenetre");
        qteLigneFraisForfaitTB = new HashMap<String, JTextField>();
        
        //Init pseudo group box
        lignesHorsFraisForfaitPanel = new JPanel();
        lignesHorsFraisForfaitPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Lignes hors frais  forfait"));
        lignesHorsFraisForfaitPanel.setLayout(new GridLayout());
        
        lignesFraisForfaitsPanel = new JPanel();
        lignesFraisForfaitsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Lignes frais forfait"));
        //lignesFraisForfaitsPanel.setLayout(new GridLayout());
        
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
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        

        
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
        layout.putConstraint(SpringLayout.WEST, selectionnerFiche, 5, SpringLayout.WEST, getContentPane());
        //layout.putConstraint(SpringLayout.NORTH, selectionFichePanel, 5, SpringLayout.SOUTH, getContentPane());
        
        
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
        
        //layout.putConstraint(SpringLayout.WEST, lignesFraisForfaitsPanel, 5, SpringLayout.WEST, this.getContentPane());
        
        add(lignesFraisForfaitsPanel);
        //lignesFraisForfaitsPanel.setPreferredSize(new Dimension(200,200));
        
        //padding
        //layout.putConstraint(SpringLayout.WEST, lignesFraisForfaitsPanel, 5, SpringLayout.NORTH, selectionFichePanel);
        layout.putConstraint(SpringLayout.NORTH, lignesFraisForfaitsPanel, 5, SpringLayout.SOUTH, selectionFichePanel);
        
        //Init TextBoxes :
        
        lignesFraisForfaitsPanel.setVisible(false);
        
        //-------------------LIGNE FRAIS HORS FORFAIT----------------------
        //préparation du panel
        
        //layout.putConstraint(SpringLayout.WEST, lignesFraisForfaitsPanel, 5, SpringLayout.WEST, this.getContentPane());
        
        add(lignesHorsFraisForfaitPanel);
        //lignesFraisForfaitsPanel.setPreferredSize(new Dimension(200,200));
        
        //padding
        //layout.putConstraint(SpringLayout.WEST, lignesFraisForfaitsPanel, 5, SpringLayout.NORTH, selectionFichePanel);
        layout.putConstraint(SpringLayout.NORTH, lignesHorsFraisForfaitPanel, 5, SpringLayout.SOUTH, lignesFraisForfaitsPanel);
        
        //Init TextBoxes :
        
        lignesHorsFraisForfaitPanel.setVisible(false);
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
		updatePanelLignesFraisForfait(ficheFrais);
		updatePanelLignesHorsFraisForfait(ficheFrais);
	}

	private void updatePanelLignesFraisForfait(FicheFrais ficheFrais)
	{
		//selectionFichePanel.setVisible(false);
		lignesFraisForfaitsPanel.setVisible(true);
		lignesFraisForfaitsPanel.setPreferredSize(new Dimension(200,ficheFrais.getLignesFraisForfait().size()*40));
		lignesFraisForfaitsPanel.removeAll();

		SpringLayout layout = new SpringLayout();
		lignesFraisForfaitsPanel.setLayout(layout);
		
		qteLigneFraisForfaitTB.clear();
		
		int nbLignes = 1;
		JPanel[] sousPanels = new JPanel[ficheFrais.getLignesFraisForfait().size()];
		for(LigneFraisForfait lff : ficheFrais.getLignesFraisForfait())
		{
			//new JDialog(this,lff.getLibelle()).setVisible(true);
			sousPanels[nbLignes - 1] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel libFrais = new JLabel(lff.getLibelle());
			sousPanels[nbLignes - 1].add(libFrais);
			JTextField tf = new JTextField(4);
			
			qteLigneFraisForfaitTB.put(lff.getIdFraisForfait(),tf);
			tf.setText(String.valueOf(lff.getQuantite()));
			sousPanels[nbLignes - 1].add(tf);
			
			if(nbLignes > 1)
				layout.putConstraint(SpringLayout.NORTH, sousPanels[nbLignes-1], 5, SpringLayout.SOUTH, sousPanels[nbLignes-2]);
			
			lignesFraisForfaitsPanel.add(sousPanels[nbLignes - 1]);
			nbLignes++;
		}
		lignesFraisForfaitsPanel.updateUI();
	}
	
	
	private void updatePanelLignesHorsFraisForfait(FicheFrais ficheFrais)
	{
		lignesHorsFraisForfaitPanel.setVisible(true);
		lignesHorsFraisForfaitPanel.removeAll();
		
		lignesHorsFraisForfaitPanel.setPreferredSize(new Dimension(600,200));
		
		//--
		SpringLayout layout = new SpringLayout();
		lignesHorsFraisForfaitPanel.setLayout(layout);
		//--
		
		JPanel p1 = new JPanel();
		
		
		//Initialisation de la table
		Object[] colonnes = {"Libellé","Date","Montant","Sélectionner"};
		Object[][] lignes = new Object[ficheFrais.getLignesFraisHorsForfait().size()][4];
		
		//hydratation de la table :
		for(int i = 0; i<ficheFrais.getLignesFraisHorsForfait().size();i++)
		{
			lignes[i][0] = ficheFrais.getLignesFraisHorsForfait().get(i).getLibelle();
			lignes[i][1] = ficheFrais.getLignesFraisHorsForfait().get(i).getDate();
			lignes[i][2] = ficheFrais.getLignesFraisHorsForfait().get(i).getMontant();
			lignes[i][3] = false;
		}
		
		lignesHorsFraisForfaitTable = new JTable(lignes,colonnes);
		lignesHorsFraisForfaitTable.getColumn("Libellé").setPreferredWidth(200);
		
		//check box dans la colonne sélectionner : 
		JCheckBox cb = new JCheckBox();
		cb.setHorizontalAlignment(JLabel.CENTER);
		lignesHorsFraisForfaitTable.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cb));
		lignesHorsFraisForfaitTable.getColumn("Sélectionner").setCellRenderer(new CheckBoxRenderer());
		
		
		//datetime colonne 
		JDateChooser calendar = new JDateChooser();
		
		lignesHorsFraisForfaitTable.getColumnModel().getColumn(1).setCellRenderer(new CalendarRender());
		
		JScrollPane scrollTable = new JScrollPane(lignesHorsFraisForfaitTable);
		lignesHorsFraisForfaitPanel.add(scrollTable);
	}
	
	public void setConnecte(boolean connecte)
	{
		se_deconnecter.setEnabled(connecte);
		se_connecter.setEnabled(!connecte);
	}

}
