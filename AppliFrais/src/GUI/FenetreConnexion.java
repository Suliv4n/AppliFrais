package GUI;

import Launcher.Launcher;
import Passerelle.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;


public class FenetreConnexion extends JDialog implements ActionListener
{
	
	private JTextField tf_id;
	private JPasswordField tf_mdp;
	
	private JButton bouton_connecter;
	private JButton bouton_annuler;
	
	public FenetreConnexion()
	{
		super(new JFrame(), "Connexion", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel lab_id = new JLabel("Identifiant : ");
		JLabel lab_mdp = new JLabel("Mot de passe : ");		
		tf_id = new JTextField(20);
		tf_mdp = new JPasswordField(20);
		
		
		bouton_annuler = new JButton("Annuler");
		bouton_connecter = new JButton("Connexion");
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		
		p1.setLayout(new FlowLayout());
		p2.setLayout(new FlowLayout());
		p3.setLayout(new FlowLayout());
		
		p1.add(lab_id);
		p1.add(tf_id);
		
		p2.add(lab_mdp);
		p2.add(tf_mdp);
		
		p3.add(bouton_connecter);
		p3.add(bouton_annuler);
		
		setLayout(new GridLayout(3,1));
		
		//Event boutons
		

		
		bouton_annuler.addActionListener(this);
		bouton_connecter.addActionListener(this);
		
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.pack();
		centrer();
	}

	
    private void centrer()
    {
    	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    	setLocation((screen.width - getSize().width)/2,(screen.height - getSize().height)/2); 
    }
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource().equals(bouton_annuler))
		{
			this.setVisible(false);
			this.dispose();
		}
		if(e.getSource().equals(bouton_connecter))
		{
			String log = tf_id.getText();
			String mdp = String.valueOf(tf_mdp.getPassword());
			
			try
			{
				if(Passerelle.verifierConnexion(log, mdp))
				{
					Launcher.estConnecte(true);
					this.setVisible(false);
					this.dispose();					
				}
				else
				{
					new JOptionPane().showMessageDialog(null,"Mot de passe ou login non valide.","Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(SQLException exc)
			{
				System.out.println("erreur = " + exc.getMessage());
			}
			
		}
		
	}
	
	
}
