package GUI;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Launcher.Launcher;


public class Fenetre extends JFrame implements ActionListener
{
   
    private JMenuBar menu;
    
    //
    private JMenu connexion;
    private JMenuItem se_connecter;
    private JMenuItem se_deconnecter;
    //
   
    public Fenetre()
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
	}
	
	public void setConnecte(boolean connecte)
	{
		se_deconnecter.setEnabled(connecte);
		se_connecter.setEnabled(!connecte);
	}

}
