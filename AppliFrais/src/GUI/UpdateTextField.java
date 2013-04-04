package GUI;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;

import Coeur.FicheFrais;

/**
 * Thread pour le invokeLater sur les textField.
 * 
 * Pour s'assurer que les textbox qui, dans la fen�tre principale, repr�sentent la "quantit�" 
 * des lignes frais forfaits, ont bien une valeur num�rique :
 * Pour emp�cher la saisie de valeur non num�rique, on supprime le texte de la TextField
 * et on r��crit la valeur pr�c�dente (qui est donc valide) d�s que la valeur change.
 * 
 * Mais on ne peut pas modifier la valeur de la TextBox pendand la gestion d'un �v�nement
 * li� � la TextBox.
 * 
 * Ce thread, appel� par la m�thode SwingUtilities.invokeLater() qui va ex�cuter le code
 * APRES la gestion de l'�v�nement. Voil� voil�.
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
