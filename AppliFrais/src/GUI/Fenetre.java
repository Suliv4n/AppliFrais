package GUI;


import java.awt.Dimension;

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
        setSize(new Dimension(500,500));
        
        //MENU------------------------------
        menu = new JMenuBar();
        
        connexion = new JMenu("Connexion");
        se_connecter = new JMenuItem("Se conencter");
        connexion.add(se_connecter);
        menu.add(connexion);
        
        setJMenuBar(menu);
        //FIN MENU--------------------------
        
        
        
    }

}
