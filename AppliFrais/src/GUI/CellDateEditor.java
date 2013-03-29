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

	public CellDateEditor() 
	{
		dateCser= new JDateChooser();
		
		
		
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
		
		Date date = dateCser.getDate();
		
		//stratégie récupérer l'année :
		String[] split = dateCser.getDate().toString().split(" ");
		int year = Integer.parseInt(split[5]);
		
		Calendar calendarMin = Calendar.getInstance();
		calendarMin.setTime(date);
		calendarMin.set(year, calendarMin.MONTH, 1);
		System.out.println(calendarMin.getTime());
			
		Calendar calendarMax = Calendar.getInstance();
		calendarMax.setTime(date);
		calendarMax.set(year, calendarMax.MONTH, 31);
		System.out.println(calendarMax.getTime());
		
		
		
		dateCser.setMinSelectableDate(calendarMin.getTime());
		dateCser.setMaxSelectableDate(calendarMax.getTime());
		return dateCser;
	}
} 