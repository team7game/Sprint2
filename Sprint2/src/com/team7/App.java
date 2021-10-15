package com.team7;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class App{
    long duration = 3;	
    
    Gui g;
    Database data;
    EventHandler ev;
    
    public App(){
        final SplashScreen splash = SplashScreen.getSplashScreen();
        
        if (splash == null)
        {
            System.out.println("SplashScreen.getSplashScreen() returned null");
        }
        else
        {
            Graphics2D graphics = splash.createGraphics();
        
            if (graphics == null) 
            {
                System.out.println("g is null");
            }
            else
            {
                for(int i=0; i<100; i++) 
                {
                    splash.update();

                    try 
                    {
                        Thread.sleep(duration*10);
                    }
                    catch(InterruptedException e) 
                    {

                    }
                }

                splash.close();
            }
        }
        
        data = new Database();
        
        g = new Gui();
        ev = new EventHandler(data);
        g.createAndShowGUI(ev);
        //g.table.getModel().addTableModelListener(ev);
        
    }

    public static void main(String[] args) {
        App app = new App();
    }
}
