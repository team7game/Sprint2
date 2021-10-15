package com.team7;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
public class Gui extends JPanel {
 
    public JTable table;
    
    public Gui() {
        super(new GridLayout(1, 0));
        String[] columnNames = {"#",
            "Red ID",
            "Red Name",
            "Green ID",
            "Green Name"};
        Object[][] data = new Object[20][5];
        // wonder if final makes this not able to be updated by database
        //final JTable table = new JTable(data, columnNames);
        TableModel dataModel = new AbstractTableModel() {
            @Override
            public int getColumnCount() {
                return 5;
            }
            @Override
            public int getRowCount() {
                return 20;
            }
            @Override
            public String getColumnName(int col) {
                return columnNames[col];
            }
            @Override
            public Object getValueAt(int row, int col) {
                return data[row][col];
            }
            @Override
            public boolean isCellEditable(int row, int col) {
                //Note that the data/cell address is constant,
                //no matter where the cell appears onscreen.
                if (col < 1)
                {
                    return false;
                }
                else if((col == 2 || col == 4) && table.getValueAt(row, col-1) == null)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public void setValueAt(Object value, int row, int col) {
                data[row][col] = value;
                fireTableCellUpdated(row, col);
            }
            //Restricts the red and green ID columns to Ints
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 3 || columnIndex == 1 ? Integer.class : super.getColumnClass(columnIndex);
            }
        };
        
        table = new JTable(dataModel);
        
        table.setPreferredScrollableViewportSize(
                new Dimension(500, 70));
        table.setFillsViewportHeight(
                true);
        table.setGridColor(Color.black);
        // set first col of nums
        for (int i = 0; i < table.getRowCount(); i++) {
            table.setValueAt(i, i, 0);
        }
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        //Add the scroll pane to this panel.
        add(scrollPane);
    }
    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    public static void createAndShowGUI(EventHandler ev) {
        //Create and set up the window.
        JFrame frame = new JFrame("Player Entry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create and set up the content pane.
        Gui newContentPane = new Gui();
        ev.setTable(newContentPane.table);
        newContentPane.table.getModel().addTableModelListener(ev);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        //Display the window.
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width / 3, screenSize.height / 3);
        frame.setVisible(true);
    }

}