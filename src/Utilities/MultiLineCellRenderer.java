package Utilities;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {
    public MultiLineCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        setFont(table.getFont());
        setText((value == null) ? "" : value.toString());
        adjustRowHeight(table, row);
        return this;
    }

    // Method to adjust row height
    private void adjustRowHeight(JTable table, int row) {
        int columnWidth = table.getColumnModel().getColumn(1).getWidth();
        setSize(new Dimension(columnWidth, 1000)); // Arbitrarily large height
        int preferredHeight = getPreferredSize().height;
        if (table.getRowHeight(row) != preferredHeight) {
            table.setRowHeight(row, preferredHeight);
        }
    }
}
