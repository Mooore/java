/*
 * IJA 2015: The Labyrinth
 */
package client.gui;


import static client.client.gui;
import client.game.Game;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author xpospi73, xdress00
 */
public class Gui extends JFrame implements Serializable {
    
    public static GridBagConstraints gbc;
    
    public static Menu menu = new Menu();
    public static NewGame newgame = new NewGame();
    public static Help help = new Help();
    public static Credits credits = new Credits();
    public static LoadGame loadgame = new LoadGame();
    
    public static Game game;
    public static GameBoard gameboard;
    
    public static String path = System.getProperty("user.dir");
    
    public Gui() {
        super("The Labyrinth");
        GridBagLayout layoutMenu;
        layoutMenu = new GridBagLayout();
        
        JLabel contentPane = new JLabel();
        try {
            contentPane.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/Labyrinth.jpg"))));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        setContentPane(contentPane);
        
        setLayout(layoutMenu);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,700);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    public void setMenu(){
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = gbc.weighty = 1.0;
        gui.add(menu.panelMenuTitle, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gui.add(menu.panelMenuButtons, gbc);
        validate();
        repaint();
    }
    
    public void setNewGame(){
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = gbc.weighty = 1.0;
        gui.add(newgame.panelNewGameTitle, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gui.add(newgame.panelNewGameControls, gbc);
        validate();
        repaint();
    }

    public void setHelp() {
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = gbc.weighty = 1.0;
        gui.add(help.panelHelpTitle, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gui.add(help.panelHelpContent, gbc);
        validate();
        repaint();
    }
    
    public void setCredits() {
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = gbc.weighty = 1.0;
        gui.add(credits.panelCreditsTitle, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gui.add(credits.panelCreditsContent, gbc);
        validate();
        repaint();
    }
    
    public void setLoadGame() {
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = gbc.weighty = 1.0;
        gui.add(loadgame.panelLoadGameTitle, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gui.add(loadgame.panelLoadGameContent, gbc);
        validate();
        repaint();
    }

    public void startNewGame() {
        int boardSize, numberOfPlayers, numberOfTreasures;
        if (newgame.radioButtonNewGameBoardSize5.isSelected()){
            boardSize = 5;
        }
        else if (newgame.radioButtonNewGameBoardSize7.isSelected()){
            boardSize = 7;
        }
        else if (newgame.radioButtonNewGameBoardSize9.isSelected()){
            boardSize = 9;
        }
        else {
            boardSize = 11;
        }
        
        if (newgame.radioButtonNewGameNumberOfPlayers2.isSelected()){
            numberOfPlayers = 2;
        }
        else if (newgame.radioButtonNewGameNumberOfPlayers3.isSelected()){
            numberOfPlayers = 3;
        }
        else {
            numberOfPlayers = 4;
        }
        
        if (newgame.radioButtonNewGameNumberOfTreasures12.isSelected()){
            numberOfTreasures = 4;
        }
        else {
            numberOfTreasures = 24;
        }
        game = new Game(boardSize, numberOfPlayers, numberOfTreasures);
        game.startNewGame();
        setGame();
        
    }
    
    public void setGame() {
        gameboard = new GameBoard();
        mainPlayingCycle("Start");
    }
    
    public void mainPlayingCycle(String command){
        int x, y;
        switch(command){
            case "Start": 
                            break;
                
            case "TurnRightCard":   x = Integer.parseInt(gameboard.textFieldGameTurnRightCardX.getText());
                                    y = Integer.parseInt(gameboard.textFieldGameTurnRightCardY.getText());
                                    
                                    if(isAvailable("turn")){
                                        if (((x <= Game.boardSize) && (x >= 1)) && ((y <= Game.boardSize) && (y >= 1))){
                                            game.turnRight(x, y);
                                            storeTurnCommand();
                                        }
                                    }
                                    
                                    break;
                
            case "TurnLeftCard":    x = Integer.parseInt(gameboard.textFieldGameTurnLeftCardX.getText());
                                    y = Integer.parseInt(gameboard.textFieldGameTurnLeftCardY.getText());
                                    
                                    if(isAvailable("turn")){
                                        if (((x <= Game.boardSize) && (x >= 1)) && ((y <= Game.boardSize) && (y >= 1))){
                                            game.turnLeft(x, y);
                                            storeTurnCommand();
                                        }
                                    }
                                    
                                    break;
                
            case "TurnRightFreeCard":   if(isAvailable("turn")){
                                            game.turnRightFreeCard();
                                            storeTurnCommand();
                                        }
                                        break;
                
            case "TurnLeftFreeCard":    if(isAvailable("turn")){
                                            game.turnLeftFreeCard();
                                            storeTurnCommand();
                                        }
                                        break;
                
            case "ShiftRight":  x = Integer.parseInt(gameboard.textFieldGameShiftRight.getText());
                                    
                                if(isAvailable("shift")){                    
                                    if (((x <= Game.boardSize) && (x >= 1)) && (x % 2 == 0)){
                                        game.shift(x, 1);
                                        storeShiftCommand();
                                    }
                                } 
                                break;
                
            case "ShiftLeft":   x = Integer.parseInt(gameboard.textFieldGameShiftLeft.getText());
                                    
                                if(isAvailable("shift")){ 
                                    if (((x <= Game.boardSize) && (x >= 1)) && (x % 2 == 0)){
                                        game.shift(x, Game.boardSize);
                                        storeShiftCommand();
                                    }
                                } 
                                break;
                
            case "ShiftDown":   x = Integer.parseInt(gameboard.textFieldGameShiftDown.getText());
                                    
                                if(isAvailable("shift")){ 
                                    if (((x <= Game.boardSize) && (x >= 1)) && (x % 2 == 0)){
                                        game.shift(1, x);
                                        storeShiftCommand();
                                    }
                                } 
                                break;
                
            case "ShiftUp":     x = Integer.parseInt(gameboard.textFieldGameShiftUp.getText());
                                    
                                if(isAvailable("shift")){ 
                                    if (((x <= Game.boardSize) && (x >= 1)) && (x % 2 == 0)){
                                        game.shift(Game.boardSize, x);
                                        storeShiftCommand();
                                    }
                                } 
                                break;
                
            case "Undo":        game.undo();
                                break;
                
            case "Go":  if(isAvailable("go")){ 
                            int goX = 0, goY = 0;
                            int FromX = 0, FromY = 0;
                            String goXtmp,goYtmp;
                            
                            goXtmp = gameboard.textFieldGameGoX.getText();
                            goYtmp = gameboard.textFieldGameGoY.getText();
                            
                            if(goXtmp.length() == 1){
                                goX = Character.getNumericValue(goXtmp.charAt(0));
                            }
                            else if(goXtmp.length() == 2){
                                goX = Character.getNumericValue(goXtmp.charAt(0) + goXtmp.charAt(1));
                            }
                            
                            if(goYtmp.length() == 1){
                                goY = Character.getNumericValue(goYtmp.charAt(0));
                            }
                            else if(goYtmp.length() == 2){
                                goY = Character.getNumericValue(goYtmp.charAt(0) + goYtmp.charAt(1));
                            }
                            
                            if(Game.currentPlayer == 1) {
                                FromX = Game.player1.positionC;
                                FromY = Game.player1.positionR;
                            }
                            else if(Game.currentPlayer == 2) {
                                FromX = Game.player2.positionC;
                                FromY = Game.player2.positionR;
                            }
                            else if(Game.currentPlayer == 3) {
                                FromX = Game.player3.positionC;
                                FromY = Game.player3.positionR;
                            }
                            else if(Game.currentPlayer == 4) {
                                FromX = Game.player4.positionC;
                                FromY = Game.player4.positionR;
                            }
                            if (((goX <= Game.boardSize) && (goX  >= 1)) && ((goY <= Game.boardSize) && (goY >= 1))){
                                if (game.go(goXtmp, goYtmp, FromX, FromY)){
                                    storeGoCommand(); 
                                }
                            }
                        }
                        break;
        }
        //System.out.println("Player " + Game.currentPlayer + ": " + Game.player1.getObtainedTreasures() + " == " + (Game.numberOfTreasures / Game.numberOfPlayers));
        switch(Game.currentPlayer){
            case 1: if(Game.player1.getObtainedTreasures() == ((int)(Game.numberOfTreasures / Game.numberOfPlayers))){
                        ResultBoard board = new ResultBoard();
                    }
                    break;
            case 2: if(Game.player2.getObtainedTreasures() == (Game.numberOfTreasures / Game.numberOfPlayers)){
                        ResultBoard board = new ResultBoard();
                    }
                    break;
            case 3: if(Game.player3.getObtainedTreasures() == (Game.numberOfTreasures / Game.numberOfPlayers)){
                        ResultBoard board = new ResultBoard();
                    }
                    break;
            case 4: if(Game.player4.getObtainedTreasures() == (Game.numberOfTreasures / Game.numberOfPlayers)){
                        ResultBoard board = new ResultBoard();
                    }
                    break;
        }
        
        refresh();
        switchPlayers();
        refresh();
    }
    
    public boolean isAvailable(String command){
        switch(command){
            case "turn":    switch(Game.currentPlayer){
                                case 1: if(Game.player1.turnCommand == false){
                                            return true;
                                        }
                                        return false;
                                case 2: if(Game.player2.turnCommand == false){
                                            return true;
                                        }
                                        return false;
                                case 3: if(Game.player3.turnCommand == false){
                                            return true;
                                        }
                                        return false;
                                case 4: if(Game.player4.turnCommand == false){
                                            return true;
                                        }
                                        return false;
                            }
                            break;
            case "shift":   switch(Game.currentPlayer){
                                case 1: if(Game.player1.shiftCommand == false){
                                            return true;
                                        }
                                        return false;
                                case 2: if(Game.player2.shiftCommand == false){
                                            return true;
                                        }
                                        return false;
                                case 3: if(Game.player3.shiftCommand == false){
                                            return true;
                                        }
                                        return false;
                                case 4: if(Game.player4.shiftCommand == false){
                                            return true;
                                        }
                                        return false;
                            }
                            break;
            case "go":  switch(Game.currentPlayer){
                            case 1: if(Game.player1.goCommand == false){
                                        return true;
                                    }
                                    return false;
                            case 2: if(Game.player2.goCommand == false){
                                        return true;
                                    }
                                    return false;
                            case 3: if(Game.player3.goCommand == false){
                                        return true;
                                    }
                                    return false;
                            case 4: if(Game.player4.goCommand == false){
                                        return true;
                                    }
                                    return false;
                        }
                        break;
            
        }
        return false;
    }
    
    public void storeTurnCommand(){
        switch(Game.currentPlayer){
            case 1: Game.player1.turnCommand = true;
                    break;
                
            case 2: Game.player2.turnCommand = true;
                    break;
            
            case 3: Game.player3.turnCommand = true;
                    break;
                
            case 4: Game.player4.turnCommand = true;
                    break;
        }
    }
    
    public void storeShiftCommand(){
        switch(Game.currentPlayer){
            case 1: Game.player1.shiftCommand = true;
                    break;
                
            case 2: Game.player2.shiftCommand = true;
                    break;
            
            case 3: Game.player3.shiftCommand = true;
                    break;
                
            case 4: Game.player4.shiftCommand = true;
                    break;
        }
    }
    
    public void storeGoCommand(){
        switch(Game.currentPlayer){
            case 1: Game.player1.goCommand = true;
                    break;
                
            case 2: Game.player2.goCommand = true;
                    break;
            
            case 3: Game.player3.goCommand = true;
                    break;
                
            case 4: Game.player4.goCommand = true;
                    break;
        }
    }
    
    public void switchPlayers(){
        switch(Game.numberOfPlayers){
            case 2: switch(Game.currentPlayer){
                        case 1: if ((Game.player1.turnCommand) && (Game.player1.shiftCommand) && (Game.player1.goCommand)){
                                    Game.currentPlayer = 2;
                                    Game.player1.turnCommand = false;
                                    Game.player1.shiftCommand = false;
                                    Game.player1.goCommand = false;
                                    Game.player1.undoRight = true;
                                }
                                break;
                        
                        case 2: if ((Game.player2.turnCommand) && (Game.player2.shiftCommand) && (Game.player2.goCommand)){
                                    Game.currentPlayer = 1;
                                    Game.player2.turnCommand = false;
                                    Game.player2.shiftCommand = false;
                                    Game.player2.goCommand = false;
                                    Game.player2.undoRight = true;
                                }
                                break;
                    }
                    break;
                
            case 3: switch(Game.currentPlayer){
                        case 1: if ((Game.player1.turnCommand) && (Game.player1.shiftCommand) && (Game.player1.goCommand)){
                                    Game.currentPlayer = 2;
                                    Game.player1.turnCommand = false;
                                    Game.player1.shiftCommand = false;
                                    Game.player1.goCommand = false;
                                    Game.player1.undoRight = true;
                                }
                                break;
                        
                        case 2: if ((Game.player2.turnCommand) && (Game.player2.shiftCommand) && (Game.player2.goCommand)){
                                    Game.currentPlayer = 3;
                                    Game.player2.turnCommand = false;
                                    Game.player2.shiftCommand = false;
                                    Game.player2.goCommand = false;
                                    Game.player2.undoRight = true;
                                }
                                break;
                            
                        case 3: if ((Game.player3.turnCommand) && (Game.player3.shiftCommand) && (Game.player3.goCommand)){
                                    Game.currentPlayer = 1;
                                    Game.player3.turnCommand = false;
                                    Game.player3.shiftCommand = false;
                                    Game.player3.goCommand = false;
                                    Game.player3.undoRight = true;
                                }
                                break;
                    }
                    break;
            
            case 4: switch(Game.currentPlayer){
                        case 1: if ((Game.player1.turnCommand) && (Game.player1.shiftCommand) && (Game.player1.goCommand)){
                                    Game.currentPlayer = 2;
                                    Game.player1.turnCommand = false;
                                    Game.player1.shiftCommand = false;
                                    Game.player1.goCommand = false;
                                    Game.player1.undoRight = true;
                                }
                                break;
                        
                        case 2: if ((Game.player2.turnCommand) && (Game.player2.shiftCommand) && (Game.player2.goCommand)){
                                    Game.currentPlayer = 3;
                                    Game.player2.turnCommand = false;
                                    Game.player2.shiftCommand = false;
                                    Game.player2.goCommand = false;
                                    Game.player2.undoRight = true;
                                }
                                break;
                            
                        case 3: if ((Game.player3.turnCommand) && (Game.player3.shiftCommand) && (Game.player3.goCommand)){
                                    Game.currentPlayer = 4;
                                    Game.player3.turnCommand = false;
                                    Game.player3.shiftCommand = false;
                                    Game.player3.goCommand = false;
                                    Game.player3.undoRight = true;
                                }
                                break;
                        
                        case 4: if ((Game.player4.turnCommand) && (Game.player4.shiftCommand) && (Game.player4.goCommand)){
                                    Game.currentPlayer = 1;
                                    Game.player4.turnCommand = false;
                                    Game.player4.shiftCommand = false;
                                    Game.player4.goCommand = false;
                                    Game.player4.undoRight = true;
                                }
                                break;
                    }
                    break;
        }
    }
    
    public void refresh(){
        //gameboard.panelGame = gameboard.panelGameBoard = gameboard.panelGameHeader = null;
        gameboard.printHeader(Game.currentPlayer);
        switch(Game.currentPlayer){
            case 1: gameboard.printObtainedTreasures(Game.player1);
                    break;
            case 2: gameboard.printObtainedTreasures(Game.player2);
                    break;
            case 3: gameboard.printObtainedTreasures(Game.player3);
                    break;
            case 4: gameboard.printObtainedTreasures(Game.player4);
                    break;
        }
        gameboard.printGameBoard();
        gameboard.printGameMatrix();
        gameboard.printFreeCard();
        gameboard.printTreasure();
        gameboard.printTreasureFreeCard();
        gameboard.printHistoryField();
        gameboard.printHistory();
        gameboard.printControls();
        gameboard.printGame();
        gui.getContentPane().removeAll();
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = gbc.weighty = 1.0;
        gui.add(gameboard.panelGameHeader, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gui.add(gameboard.panelGame, gbc);
        validate();
        repaint();
    }
}
