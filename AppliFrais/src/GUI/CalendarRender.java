package GUI;

import java.awt.Component;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

public class CalendarRender implements TableCellRenderer 
{

    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row,
            int column) 
    {
    	JDateChooser dateChooser = new JDateChooser();
    	SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
    	
    	
    	try 
    	{
			Date date = df.parse(value.toString()); //ça parse ou ça casse
			dateChooser.setDate(date);
		} 
    	catch (ParseException e) 
		{
			e.printStackTrace();
		}
    	return dateChooser;
    }

}
