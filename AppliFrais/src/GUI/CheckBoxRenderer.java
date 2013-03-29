package GUI;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
//Classe touvée sur internet

/**
 * Affiche des checkBox dans une colonne d'une JTable.
 * 
 * @author ?
 *
 */
public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {

    CheckBoxRenderer() {
      setHorizontalAlignment(JLabel.CENTER);
    }

    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row,
            int column) {

        if (value instanceof JComboBox) {
            return (JComboBox) value;
        }
        if (value instanceof Boolean) {
            JCheckBox cb = new JCheckBox();
            cb.setSelected(((Boolean) value).booleanValue());
            cb.setHorizontalAlignment(JLabel.CENTER);
            return cb;
        }
        if (value instanceof JCheckBox) {
            return (JCheckBox) value;
        }
        return new JTextField(value.toString());
    }
}
