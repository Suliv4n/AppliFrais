package GUI;


import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

import org.w3c.dom.Document;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import Coeur.FicheFrais;
import Coeur.LigneFraisForfait;
import Coeur.LigneFraisHorsForfait;
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
public class Fenetre extends JFrame implements ActionListener, TableModelListener, DocumentListener
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
    
    private HashMap<javax.swing.text.Document, LigneFraisForfait> qteLigneFraisForfaitTB;
    
    //boutons ligne frais hors forfait
    private JButton tout_selectionner;
    private JButton tout_deselectionner;
    private JButton accepter;
    private JButton refuser;
    //
    
    //
    private JPanel panVal;
    private JButton valider;
    //
    public Fenetre() throws SQLException
    {
    	
    	
        super("Fenetre");
        qteLigneFraisForfaitTB = new HashMap<javax.swing.text.Document, LigneFraisForfait>();
        
        //Init pseudo group box
        lignesHorsFraisForfaitPanel = new JPanel();
        lignesHorsFraisForfaitPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white),"Lignes hors frais  forfait"));
        lignesHorsFraisForfaitPanel.setLayout(new GridLayout());
        
        lignesFraisForfaitsPanel = new JPanel();
        lignesFraisForfaitsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white),"Lignes frais forfait"));
        
        
        selectionFichePanel = new JPanel();
        selectionFichePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white),"Selectionner une fiche"));
        selectionFichePanel.setLayout(new GridLayout(3,2));//
        //----------------------------------
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700, 700);
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
        
        
        
        tout_deselectionner = new JButton("Tout déselectionner");
        tout_selectionner = new JButton("Tout sélectionner");
        refuser = new JButton("Refuser");
        accepter = new JButton("Accepter");
        
		tout_deselectionner.addActionListener(this);
		tout_selectionner.addActionListener(this);
		accepter.addActionListener(this);
		refuser.addActionListener(this);
        
        
        lignesHorsFraisForfaitPanel.setVisible(false);
        
        
        
        //dernier panel => bouton valider :
        valider = new JButton("Valider la fiche");
        panVal = new JPanel();
        panVal.add(valider);
        add(panVal);
        panVal.setVisible(false);
        valider.addActionListener(this);
        layout.putConstraint(SpringLayout.NORTH, panVal, 5, SpringLayout.SOUTH, lignesHorsFraisForfaitPanel);   
        
        //Couleur de la société:
        lignesFraisForfaitsPanel.setBackground(new Color(119,170,221));
        lignesHorsFraisForfaitPanel.setBackground(new Color(119,170,221));
        selectionFichePanel.setBackground(new Color(119,170,221));
        panVal.setBackground(new Color(119,170,221));
        p1.setBackground(new Color(119,170,221));
        p2.setBackground(new Color(119,170,221));
        p3.setBackground(new Color(119,170,221));
        
        getContentPane().setBackground(new Color(119,170,221));
        
        lab_listeFicheFrais.setForeground(Color.white);
        lab_listeVisiteurs.setForeground(Color.white);
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
		
		//-----BOUTONS LIGNES HORS FRAIS FORFAIT--------
		else if(e.getSource().equals(tout_selectionner))
		{
			this.toutSelectionner();
		}
		else if(e.getSource().equals(tout_deselectionner))
		{
			this.toutDeselectionner();
		}
		else if(e.getSource().equals(refuser))
		{
			this.refuserLignesFraisHorsForfait();
		}
		else if(e.getSource().equals(accepter))
		{
			this.accepterLignesFraisHorsForfait();
		}
		else if(e.getSource().equals(valider))
		{
			this.validerFiche();
		}		
	}
	
	private void validerFiche() 
	{
		try 
		{
			current_fiche.valider();
			this.setFiche(current_fiche);
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(this,
				    "Une erreur s'est produite lors de la connexion avec la base de données : "
					+ e.getMessage(),
				    "Erreur",
				    JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Refuse les lignes frais hors forfait sélectionnées.
	 */
	private void refuserLignesFraisHorsForfait() 
	{
		for(int i = 0; i<lignesHorsFraisForfaitTable.getRowCount();i++)
		{
			if((Boolean) lignesHorsFraisForfaitTable.getValueAt(i, 3))
			{
				this.current_fiche.getLignesFraisHorsForfait().get(i).refuser();
				
				String libelle = current_fiche.getLignesFraisHorsForfait().get(i).getLibelle();
				
				this.lignesHorsFraisForfaitTable.setValueAt(current_fiche.getLignesFraisHorsForfait().get(i).getMontant(), i, 2);
			}
		}
	}
	
	/**
	 * Accepte les lignes frais hors forfait sélectionnées.
	 */
	private void accepterLignesFraisHorsForfait() 
	{
		for(int i = 0; i<lignesHorsFraisForfaitTable.getRowCount();i++)
		{
			if((Boolean) lignesHorsFraisForfaitTable.getValueAt(i, 3))
			{
				this.current_fiche.getLignesFraisHorsForfait().get(i).accepter();
				
				String libelle = current_fiche.getLignesFraisHorsForfait().get(i).getLibelle();
				
				double montant = current_fiche.getLignesFraisHorsForfait().get(i).getMontant();
				
				System.out.println(montant);
				
				this.lignesHorsFraisForfaitTable.setValueAt(montant, i, 2);
				
			}
		}
	}

	/**
	 * Coche toutes les lignes de la table des lignes frais hors forfait.
	 */
	private void toutSelectionner()
	{
		for(int i = 0; i<lignesHorsFraisForfaitTable.getRowCount();i++)
		{
			lignesHorsFraisForfaitTable.setValueAt(true, i, 3);
		}
	}
	
	/**
	 * Décoche toutes les lignes de la table des lignes frais hors forfait.
	 */
	private void toutDeselectionner()
	{
		for(int i = 0; i<lignesHorsFraisForfaitTable.getRowCount();i++)
		{
			lignesHorsFraisForfaitTable.setValueAt(false, i, 3);
		}
	}

	/**
	 * Met à jour les composants de la fenêtre à la sélection d'une fiche.
	 * 
	 * @param ficheFrais
	 */
	private void setFiche(FicheFrais ficheFrais) 
	{
		current_fiche = ficheFrais;
		updatePanelLignesFraisForfait(ficheFrais);
		updatePanelLignesHorsFraisForfait(ficheFrais);
		panVal.setVisible(true);
		
		if(!current_fiche.getIdEtat().equals("CL"))
		{
			valider.setEnabled(false);
			tout_selectionner.setEnabled(false);
			tout_deselectionner.setEnabled(false);
		}
		else
		{
			valider.setEnabled(true);
			tout_selectionner.setEnabled(true);
			tout_deselectionner.setEnabled(true);
		}
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
			libFrais.setForeground(Color.white);
			sousPanels[nbLignes - 1].setBackground(new Color(119,170,221));
			sousPanels[nbLignes - 1].add(libFrais);
			JTextField tf = new JTextField(4);
			
			if(!current_fiche.getIdEtat().equals("CL"))
			{
				tf.setEnabled(false);
			}
			else
			{
				tf.setEnabled(true);
			}
			qteLigneFraisForfaitTB.put(tf.getDocument(),lff);
			tf.setText(String.valueOf(lff.getQuantite()));
			tf.getDocument().addDocumentListener(this);
			
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
		
		lignesHorsFraisForfaitPanel.setPreferredSize(new Dimension(600,230));
		
		//--
		SpringLayout layout = new SpringLayout();
		lignesHorsFraisForfaitPanel.setLayout(layout);
		//--
		
		JPanel p1 = new JPanel();
		p1.setBackground(new Color(119,170,221));
		
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
		
		lignesHorsFraisForfaitTable.getModel().addTableModelListener(this);
		lignesHorsFraisForfaitTable.getColumn("Libellé").setPreferredWidth(200);
		lignesHorsFraisForfaitTable.getColumn("Date").setPreferredWidth(70);
		lignesHorsFraisForfaitTable.getColumn("Montant").setPreferredWidth(50);
		
		
		//check box dans la colonne sélectionner : 
		JCheckBox cb = new JCheckBox();
		cb.setHorizontalAlignment(JLabel.CENTER);
		lignesHorsFraisForfaitTable.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cb));
		lignesHorsFraisForfaitTable.getColumn("Sélectionner").setCellRenderer(new CheckBoxRenderer());
		
		
		//dateChooser colonne 
		JDateChooser calendar = new JDateChooser();
		
		lignesHorsFraisForfaitTable.getColumnModel().getColumn(1).setCellRenderer(new CalendarRender());
		
		if(ficheFrais.getLignesFraisHorsForfait().size()>0)
			lignesHorsFraisForfaitTable.getColumnModel().getColumn(1).setCellEditor(new CellDateEditor((Date)lignesHorsFraisForfaitTable.getValueAt(0, 1)));
		
		JScrollPane scrollTable = new JScrollPane(lignesHorsFraisForfaitTable);
		scrollTable.setPreferredSize(new Dimension(550,150));
		
		scrollTable.setBackground(new Color(119,170,221));
		
		p1.add(scrollTable);
		lignesHorsFraisForfaitPanel.add(p1);
		
		//-----------BOUTONS------------------------------

		JPanel p2 = new JPanel();
		p2.setBackground(new Color(119,170,221));
		
		p2.add(tout_selectionner);
		p2.add(tout_deselectionner);
		p2.add(accepter);
		p2.add(refuser);
		
		accepter.setEnabled(false);
		refuser.setEnabled(false);
		
		scrollTable.setVisible(true);
		layout.putConstraint(SpringLayout.NORTH, p2, 5, SpringLayout.SOUTH, p1);
		lignesHorsFraisForfaitTable.getTableHeader().setReorderingAllowed(false);
		lignesHorsFraisForfaitPanel.add(p2);
		
		
		
		if(!current_fiche.getIdEtat().equals("CL"))
		{
			lignesHorsFraisForfaitTable.setEnabled(false);
			tout_selectionner.setEnabled(false);
			tout_deselectionner.setEnabled(false);
		}
		else
		{
			lignesHorsFraisForfaitTable.setEnabled(true);
			tout_selectionner.setEnabled(true);
			tout_deselectionner.setEnabled(true);
		}
		
		
	}
	
	public void setConnecte(boolean connecte)
	{
		se_deconnecter.setEnabled(connecte);
		se_connecter.setEnabled(!connecte);
		
		//---
		if(!connecte)
		{
			lignesFraisForfaitsPanel.setVisible(false);
			lignesHorsFraisForfaitPanel.setVisible(false);
		}
		liste_fichesFrais.setEnabled(connecte);
		liste_visiteurs.setEnabled(connecte);
		selectionnerFiche.setEnabled(connecte);
	}

	@Override
	public void tableChanged(TableModelEvent e) 
	{
		if(e.getType() == TableModelEvent.UPDATE)
		{
			boolean ligneSelectionnee = false; 
			for(int i=0; i<lignesHorsFraisForfaitTable.getRowCount() && !ligneSelectionnee;i++)
			{
				ligneSelectionnee = (Boolean)lignesHorsFraisForfaitTable.getValueAt(i, 3);
			}
			
			accepter.setEnabled(ligneSelectionnee);
			refuser.setEnabled(ligneSelectionnee);
			
			
			for(int i=0; i<lignesHorsFraisForfaitTable.getRowCount();i++)
			{

				try
				{
					double montant = Double.parseDouble(lignesHorsFraisForfaitTable.getValueAt(i, 2).toString());
					
					if(montant < 0)
					{
						throw new Exception();
					}
					
					current_fiche.getLignesFraisHorsForfait().get(i).setMontant(montant);

					if(montant == 0)
					{
						//current_fiche.getLignesFraisHorsForfait().get(i).refuser();
					}
					else
					{
						current_fiche.getLignesFraisHorsForfait().get(i).accepter();
					}
					
					if(!lignesHorsFraisForfaitTable.getValueAt(i, 0).equals(current_fiche.getLignesFraisHorsForfait().get(i).getLibelle()))
					{
						lignesHorsFraisForfaitTable.setValueAt(current_fiche.getLignesFraisHorsForfait().get(i).getLibelle(), i, 0);
					}
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(this,
						    "La valeur saisie n'est pas valide.",							
						    "Erreur",
						    JOptionPane.ERROR_MESSAGE);
					
					lignesHorsFraisForfaitTable.setValueAt(current_fiche.getLignesFraisHorsForfait().get(i).getMontant(), i, 2);
				}
				
				Object o = lignesHorsFraisForfaitTable.getValueAt(i, 1);
				
				if(o instanceof Date)
				{
					java.sql.Date d = new java.sql.Date(((Date)o).getTime());
					current_fiche.getLignesFraisHorsForfait().get(i).setDate(d);
				}
			}
		}
		
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) 
	{
		try 
		{
			arg0.getDocument().insertString(0, "0", null);
		} 
		catch (BadLocationException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) 
	{
		try
		{
			int qte = Integer.parseInt(arg0.getDocument().getText(0, arg0.getDocument().getLength()));
			qteLigneFraisForfaitTB.get(arg0.getDocument()).setQuantite(qte);
		}
		catch(Exception exc)
		{
			int valeur = qteLigneFraisForfaitTB.get(arg0.getDocument()).getQuantite();
			SwingUtilities.invokeLater(new UpdateTextField(arg0, valeur));
		}
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) 
	{
		try
		{
			int qte = Integer.parseInt(arg0.getDocument().getText(0, arg0.getDocument().getLength()));
			qteLigneFraisForfaitTB.get(arg0.getDocument()).setQuantite(qte);
			System.out.println(current_fiche.getLignesFraisForfait().get(0).getQuantite());
			System.out.println(qte);
		}
		catch(Exception exc)
		{
			qteLigneFraisForfaitTB.get(arg0.getDocument()).setQuantite(0);
		}
	}

}
