/*
 * IJA 2015: The Labyrinth
 */
package client.undo;

import client.game.Game;
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
    
    public void cleanCommands() {
        commands =  new ArrayList<>();
        lastCommand = 0;
    }
    
    public String readLastCommand() {
        return commands.get(lastCommand-1);
    }
    
    public String invertCommand(String command) {
        String invertedCommand = null,rc;
        if(command.matches("^(t|(-t))(([0-9])([0-9])?([:])([0-9])([0-9])?)$") == true) {
            rc = command.replaceAll("(t|(-t))", "");
            int row, col;
            if(rc.length() == 3){
                row = Character.getNumericValue(rc.charAt(0));
                col = Character.getNumericValue(rc.charAt(2));
            }
            else if(rc.length() == 5){
                row = Character.getNumericValue(rc.charAt(0) + rc.charAt(1));
                col = Character.getNumericValue(rc.charAt(3) + rc.charAt(4));
            }
            else {
                if(":".equals(String.valueOf(rc.charAt(1)))){
                    row = Character.getNumericValue(rc.charAt(0));
                    col = Character.getNumericValue(rc.charAt(2) + rc.charAt(3));
                }
                else {
                    row = Character.getNumericValue(rc.charAt(0) + rc.charAt(1));
                    col = Character.getNumericValue(rc.charAt(3));
                }
            }
            invertedCommand = ("tl" + row + ":" + col);
        }     
        else if(command.matches("^(tl|(-tl))(([0-9])([0-9])?([:])([0-9])([0-9])?)$") == true) {
            rc = command.replaceAll("(tl|(-tl))", "");
            int row, col;
            if(rc.length() == 3){
                row = Character.getNumericValue(rc.charAt(0));
                col = Character.getNumericValue(rc.charAt(2));
                
            }
            else if(rc.length() == 5){
                row = Character.getNumericValue(rc.charAt(0) + rc.charAt(1));
                col = Character.getNumericValue(rc.charAt(3) + rc.charAt(4));
              
            }
            else {
                if(":".equals(String.valueOf(rc.charAt(1)))){
                    row = Character.getNumericValue(rc.charAt(0));
                    col = Character.getNumericValue(rc.charAt(2) + rc.charAt(3));
                }
                else {
                    row = Character.getNumericValue(rc.charAt(0) + rc.charAt(1));
                    col = Character.getNumericValue(rc.charAt(3));
                }
            }
            invertedCommand = ("t" + row + ":" + col);
        }     
        else if(command.matches("^(s|(-s))(([0-9])([0-9])?([:])([0-9])([0-9])?)$") == true) {
            rc = command.replaceAll("(s|(-s))", "");
            int row, col;
            if(rc.length() == 3){
                row = Character.getNumericValue(rc.charAt(0));
                col = Character.getNumericValue(rc.charAt(2));
            }
            else if(rc.length() == 5){
                row = Character.getNumericValue(rc.charAt(0) + rc.charAt(1));
                col = Character.getNumericValue(rc.charAt(3) + rc.charAt(4));
            }
            else {
                if(":".equals(String.valueOf(rc.charAt(1)))){
                    row = Character.getNumericValue(rc.charAt(0));
                    col = Character.getNumericValue(rc.charAt(2) + rc.charAt(3));
                }
                else {
                    row = Character.getNumericValue(rc.charAt(0) + rc.charAt(1));
                    col = Character.getNumericValue(rc.charAt(3));
                }
            }
            
            if (row == 1) {
                invertedCommand = ("s" + Game.boardSize + ":" + col);
            }
            else if(row == Game.boardSize) {
                invertedCommand = ("s" + 1 + ":" + col);
            }
            else if (col == 1) {
                invertedCommand = ("s" + row + ":" + Game.boardSize);
            }
            else if (col == Game.boardSize) {
                invertedCommand = ("s" + row + ":" + 1);
            }
        }
        else if(command.matches("^(tf|(-tf))$") == true) {
            invertedCommand = "tlf";
        }
        else if(command.matches("^(tlf|(-tlf))$") == true) {
            invertedCommand = "tf";
        }
        else if(command.matches("^(go|(-go))([0-9])([0-9])?([:])([0-9])([0-9])?([-])([0-9])([0-9])?([:])([0-9])([0-9])?$") == true) {
            rc = command.replaceAll("(go|(-go))", "");
            int goX, goY, FromX, FromY;
            goX = goY = FromX = FromY = 0;
            String go[] = rc.split("-");
            for(int i = 0; i < 2; i++){
                if (go[i].equals("-") != true){
                    String part[] = go[i].split(":");
                    for(int j = 0; j < 2; j++){
                        if((i == 0) && (j == 0)){
                            goX = Integer.parseInt(part[j]);
                        }
                        else if((i == 0) && (j == 1)){
                            goY = Integer.parseInt(part[j]);
                        }
                        else if((i == 1) && (j == 0)){
                            FromX = Integer.parseInt(part[j]);
                        }
                        else if((i == 1) && (j == 1)){
                            FromY = Integer.parseInt(part[j]);
                        }
                    }
                }
            }
            invertedCommand = "go" + FromX + ":" + FromY + "-" + goX + ":" + goY;
        }
        return invertedCommand;
    } 
}
