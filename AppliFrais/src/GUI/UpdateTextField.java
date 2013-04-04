package GUI;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;

import Coeur.FicheFrais;

/**
 * Thread pour le invokeLater sur les textField.
 * 
 * Pour s'assurer que les textbox qui, dans la fenêtre principale, représentent la "quantité" 
 * des lignes frais forfaits, ont bien une valeur numérique :
 * Pour empêcher la saisie de valeur non numérique, on supprime le texte de la TextField
 * et on réécrit la valeur précédente (qui est donc valide) dès que la valeur change.
 * 
 * Mais on ne peut pas modifier la valeur de la TextBox pendand la gestion d'un évènement
 * lié à la TextBox.
 * 
 * Ce thread, appelé par la méthode SwingUtilities.invokeLater() qui va exécuter le code
 * APRES la gestion de l'évènement. Voilà voilà.
 * 
 * @author gsh
 *
 */
public class UpdateTextField implements Runnable
{

	private DocumentEvent e;
	private int valeur;
	
	public UpdateTextField(DocumentEvent e, int val)
	{
		this.e = e;
		this.valeur = val;
	}
	
	@Override
	public void run() 
	{
		try 
		{
			if(e.getDocument().getText(0, e.getDocument().getLength()).isEmpty())
			{
				e.getDocument().insertString(0,"0", null);
			}
			else
			{
				//supprime tout
				e.getDocument().remove(0, e.getDocument().getLength());
				//remplace par l'ancienne valeur :
				e.getDocument().insertString(0, String.valueOf(valeur), null);
			}
				

			
		} 
		catch (BadLocationException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
}
