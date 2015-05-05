/*
 * IJA 2015: The Labyrinth
 */
package client;

import client.gui.Gui;

/**
 *
 * @author xpospi73, xdress00
 */

/**
 * This class creates new Gui and menu after lauching the application.
 */
public class client {
    
    /**
     * Represents the Gui.
     */
    public static Gui gui;
    
    /**
     * Main method creates a new Gui and sets a game menu.
     * @param args no arguments needed.
     */
    public static void main(String[] args) {
        gui = new Gui();
        gui.setMenu();
    }  
}
