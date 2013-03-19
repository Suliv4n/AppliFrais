package GUI;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FenetreConnexion extends JDialog
{
	
	JTextField tf_id;
	JTextField tf_mdp;
	
	public FenetreConnexion()
	{
		super(new JFrame(), "Connexion", true);
		JLabel lab_id = new JLabel("Identifiant : ");
		JLabel lab_mdp = new JLabel("Mot de passe : ");
		
		tf_id = new JTextField();
		tf_mdp = new JTextField();
		
		this.add(lab_id);
		this.add(tf_id);
	}
	
	
}
