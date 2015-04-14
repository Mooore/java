/*
 * IJA 2015: Homework 3
 */
package client.undo;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author xpospi73, xdress00
 */
public class undo {
    
    private List<String> commands = new ArrayList<String>();
    private int lastCommand;
    
    public undo() {
        lastCommand = 0;   
    }
    
    public void storeCommand(String command) {
        commands.add(command);
        lastCommand++;
    }
    
    private String readLastCommand() {
        return commands.get(lastCommand-1);
    }
    
    public void undoLastCommand() {
        if(lastCommand > 0) {
            System.out.println(readLastCommand());
            commands.remove(lastCommand - 1);
            lastCommand--;
        }
        else {
            System.out.println("Nothing to undo.");
        }
    }
  
}
