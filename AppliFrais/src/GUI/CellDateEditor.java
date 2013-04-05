package GUI;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class CellDateEditor extends AbstractCellEditor implements TableCellEditor{

	private JDateChooser dateCser;

	public CellDateEditor(Date date) 
	{		
		//stratégie récupérer l'année :
		String[] split = date.toString().split("-");
		int year = Integer.parseInt(split[0]);
		int month = Integer.parseInt(split[1]) - 1;
		
		
		Calendar calendarMin = Calendar.getInstance();
		calendarMin.setTime(date);
		calendarMin.set(year, month, 1);
			
		Calendar calendarMax = Calendar.getInstance();
		calendarMax.setTime(date);
		calendarMax.set(year, month, 31);
		
		dateCser = new JDateChooser();
		
		dateCser.setMinSelectableDate(calendarMin.getTime());
		dateCser.setMaxSelectableDate(calendarMax.getTime());
		dateCser.setDate(date);
		
		
		JTextFieldDateEditor text =(JTextFieldDateEditor)dateCser.getDateEditor().getUiComponent();
		text.setEditable(false);
	}


	//Implement the one CellEditor method that AbstractCellEditor doesn't.
	public Object getCellEditorValue() 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


		if(dateCser.getDate()!= null)
			return dateFormat.format(dateCser.getDate());
		return null;
	}

	//Implement the one method defined by TableCellEditor.
	public Component getTableCellEditorComponent(JTable table,
			Object value,
			boolean isSelected,
			int row,
			int column) {
		

		return dateCser;
	}
} 