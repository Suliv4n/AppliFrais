package GUI;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import Coeur.FicheFrais;


public class CouleurComboBox  implements ListCellRenderer
{
	private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus)
	{
		JLabel render = (JLabel) defaultRenderer.getListCellRendererComponent(list,value,index,isSelected, cellHasFocus);
		
		if(value != null && value instanceof FicheFrais)
		{
			render.setForeground(((FicheFrais)value).getColor());
		}
		return render;		
	}
	
}
