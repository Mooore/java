/*
 * IJA 2015: The Labyrinth
 */
package client;

import client.gui.Gui;
import client.tui.*;

/**
 *
 * @author xpospi73, xdress00
 */
public class client {
    
    public static Gui gui;
    public static Tui tui;
    
    public static void main(String[] args) {
        if (args.length != 1){
            gui = new Gui();
            gui.setMenu();
        }
        else if("-tui".equals(args[0])){
            tui = new Tui(7);
            tui.tuiRun();
        }
    }
    
}
