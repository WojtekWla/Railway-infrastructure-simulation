package GUI_Project.Interface;

import GUI_Project.Rail.Station;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.TableView;
import java.awt.*;

public class CellRenderer extends DefaultTableCellRenderer {



    public static int currColor = 0;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel jLabel = new JLabel();
        jLabel.setOpaque(true);
        if(value != null) {
            jLabel.setText(value.toString());
            jLabel.setBackground(((Station) value).getColor());
            if(currColor == 100)
                currColor = 0;
        }else
            jLabel.setBackground(Color.BLACK);
        return jLabel;
    }
}
