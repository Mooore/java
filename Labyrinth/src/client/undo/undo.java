/*
 * IJA 2015: The Labyrinth
 */
package client.undo;

import static client.client.tui;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author xpospi73, xdress00
 */
public class undo {
    
    public List<String> commands;
    public int lastCommand;
    
    public undo() {
        lastCommand = 0;   
        commands =  new ArrayList<>();
    }
    
    public void storeCommand(String command) {
        commands.add(command);
        lastCommand++;
    }
    
    public String readLastCommand() {
        return commands.get(lastCommand-1);
    }
    
    public String invertCommand(String command) {
        String invertedCommand = null,rc;
        if(command.matches("^(t|(-t))([0-9]{2})$") == true) {
            rc = command.replaceAll("(t|(-t))", "");
            int row = Character.getNumericValue(rc.charAt(0));
            int col = Character.getNumericValue(rc.charAt(1));
            invertedCommand = ("tl" + row + col);
        }     
        else if(command.matches("^(tl|(-tl))([0-9]{2})$") == true) {
            rc = command.replaceAll("(tl|(-tl))", "");
            int row = Character.getNumericValue(rc.charAt(0));
            int col = Character.getNumericValue(rc.charAt(1));
            invertedCommand = ("t" + row + col);
        }     
        else if(command.matches("^(s|(-s))([0-9]{2})$") == true) {
            rc = command.replaceAll("(s|(-s))", "");
            int row = Character.getNumericValue(rc.charAt(0));
            int col = Character.getNumericValue(rc.charAt(1));
            if (row == 1) {
                invertedCommand = ("s" + tui.getSize() + col);
            }
            else if(row == tui.getSize()) {
                invertedCommand = ("s" + 1 + col);
            }
            else if (col == 1) {
                invertedCommand = ("s" + row + tui.getSize());
            }
            else if (col == tui.getSize()) {
                invertedCommand = ("s" + row + 1);
            }
        }
        return invertedCommand;
    } 
}
