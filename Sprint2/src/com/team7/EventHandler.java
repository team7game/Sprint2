package com.team7;

import java.awt.event.*;
import java.util.*;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class EventHandler implements TableModelListener
{
    //Arrays will be of length 3; first num is cell value, second num is cell row, third num is cell column
    List<int[]> occupiedIDCells = new ArrayList<int[]>();
    JTable table;
    Database database;
    
    public EventHandler(Database db)
    {
        database = db;
    }
    
    public void setTable(JTable tb)
    {
        table = tb;
    }
    
    public void tableChanged(TableModelEvent e)
    {  
        
        int c = e.getColumn();
        int r = e.getLastRow();
        
        if(c == 0 || table.getValueAt(r, c) == null)
        {
            return;
        }
        
        database.connect();
        
        if(c == 1 || c == 3)
        {
           if(database.checkID((int)table.getValueAt(r, c)))
           {
                int id = (int)table.getValueAt(r, c);
                System.out.println(id);
                table.setValueAt(database.getName(id), r, c+1);
           }
           else
           {
               
           }  
        }
        
        if((c == 2 || c == 4) && !database.checkID((int)table.getValueAt(r, c-1)))
        {
            String username = table.getValueAt(r, c).toString();
            database.createPlayer((int)table.getValueAt(r, c-1), username);
        }
        
        
        database.close();
    }
}






