/*
 * IJA 2015: The Labyrinth
 */
package client;

import client.view.*;
import client.tui.*;

/**
 *
 * @author xpospi73, xdress00
 */
public class client {
    
    public static void main(String[] args) {
        if (args.length != 1){
            Gui gui = new Gui();
            gui.Menu();
        }
        else if("-tui".equals(args[0])){
            Tui tui = new Tui(7);
            tui.tuiRun();
        }
    }
    
}
