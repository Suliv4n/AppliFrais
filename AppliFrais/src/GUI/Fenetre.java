package GUI;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;


public class Fenetre extends JFrame
{
   
    private JMenuBar menu;
    private JMenu connexion;
    private JMenuItem se_connecter;
   
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
        se_connecter = new JMenuItem("Se conencter");
        connexion.add(se_connecter);
        menu.add(connexion);
        
        setJMenuBar(menu);
        //FIN MENU--------------------------
    }
    
    private void centrer()
    {
    	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    	setLocation((screen.width - getSize().width)/2,(screen.height - getSize().height)/2); 
    }

}
