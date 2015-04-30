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
public class Gui extends JFrame {
    
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
        setSize(1150,850);
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
            numberOfTreasures = 12;
        }
        else {
            numberOfTreasures = 24;
        }
        System.out.println(Integer.toString(numberOfPlayers));
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
                                    
                                    if (((x <= Game.boardSize) && (x >= 0)) && ((y <= Game.boardSize) && (y >= 0))){
                                        game.turnRight(x, y);
                                        storeTurnCommand();
                                    }
                                    
                                    break;
                
            case "TurnLeftCard":    x = Integer.parseInt(gameboard.textFieldGameTurnLeftCardX.getText());
                                    y = Integer.parseInt(gameboard.textFieldGameTurnLeftCardY.getText());
                                    
                                    if (((x <= Game.boardSize) && (x >= 0)) && ((y <= Game.boardSize) && (y >= 0))){
                                        game.turnLeft(x, y);
                                        storeTurnCommand();
                                    }
                                    
                                    break;
                
            case "TurnRightFreeCard":   game.turnRightFreeCard();
                                        storeTurnCommand();
                                        break;
                
            case "TurnLeftFreeCard":    game.turnLeftFreeCard();
                                        storeTurnCommand();
                                        break;
                
            case "ShiftRight":  x = Integer.parseInt(gameboard.textFieldGameShiftRight.getText());
                                    
                                if (((x <= Game.boardSize) && (x >= 0)) && (x % 2 == 0)){
                                    game.shift(x, 1);
                                    storeShiftCommand();
                                } 
                                break;
                
            case "ShiftLeft":   x = Integer.parseInt(gameboard.textFieldGameShiftLeft.getText());
                                    
                                if (((x <= Game.boardSize) && (x >= 0)) && (x % 2 == 0)){
                                    game.shift(x, Game.boardSize);
                                    storeShiftCommand();
                                } 
                                break;
                
            case "ShiftDown":   x = Integer.parseInt(gameboard.textFieldGameShiftDown.getText());
                                    
                                if (((x <= Game.boardSize) && (x >= 0)) && (x % 2 == 0)){
                                    game.shift(1, x);
                                    storeShiftCommand();
                                } 
                                break;
                
            case "ShiftUp":     x = Integer.parseInt(gameboard.textFieldGameShiftUp.getText());
                                    
                                if (((x <= Game.boardSize) && (x >= 0)) && (x % 2 == 0)){
                                    game.shift(Game.boardSize, x);
                                    storeShiftCommand();
                                } 
                                break;
                
            case "Go":  storeGoCommand(); 
                        break;
        }
        
        switch(Game.numberOfPlayers){
            case 2: switch(game.currentPlayer){
                        case 1: if ((Game.player1.turnCommand) && (Game.player1.shiftCommand) && (Game.player1.goCommand)){
                                    game.currentPlayer = 2;
                                    Game.player1.turnCommand = false;
                                    Game.player1.shiftCommand = false;
                                    Game.player1.goCommand = false;
                                }
                                break;
                        
                        case 2: if ((Game.player2.turnCommand) && (Game.player2.shiftCommand) && (Game.player2.goCommand)){
                                    game.currentPlayer = 1;
                                    Game.player2.turnCommand = false;
                                    Game.player2.shiftCommand = false;
                                    Game.player2.goCommand = false;
                                }
                                break;
                    }
                    break;
                
            case 3: switch(game.currentPlayer){
                        case 1: if ((Game.player1.turnCommand) && (Game.player1.shiftCommand) && (Game.player1.goCommand)){
                                    game.currentPlayer = 2;
                                    Game.player1.turnCommand = false;
                                    Game.player1.shiftCommand = false;
                                    Game.player1.goCommand = false;
                                }
                                break;
                        
                        case 2: if ((Game.player2.turnCommand) && (Game.player2.shiftCommand) && (Game.player2.goCommand)){
                                    game.currentPlayer = 3;
                                    Game.player2.turnCommand = false;
                                    Game.player2.shiftCommand = false;
                                    Game.player2.goCommand = false;
                                }
                                break;
                            
                        case 3: if ((Game.player3.turnCommand) && (Game.player3.shiftCommand) && (Game.player3.goCommand)){
                                    game.currentPlayer = 1;
                                    Game.player3.turnCommand = false;
                                    Game.player3.shiftCommand = false;
                                    Game.player3.goCommand = false;
                                }
                                break;
                    }
                    break;
            
            case 4: switch(game.currentPlayer){
                        case 1: if ((Game.player1.turnCommand) && (Game.player1.shiftCommand) && (Game.player1.goCommand)){
                                    game.currentPlayer = 2;
                                    Game.player1.turnCommand = false;
                                    Game.player1.shiftCommand = false;
                                    Game.player1.goCommand = false;
                                }
                                break;
                        
                        case 2: if ((Game.player2.turnCommand) && (Game.player2.shiftCommand) && (Game.player2.goCommand)){
                                    game.currentPlayer = 3;
                                    Game.player2.turnCommand = false;
                                    Game.player2.shiftCommand = false;
                                    Game.player2.goCommand = false;
                                }
                                break;
                            
                        case 3: if ((Game.player3.turnCommand) && (Game.player3.shiftCommand) && (Game.player3.goCommand)){
                                    game.currentPlayer = 4;
                                    Game.player3.turnCommand = false;
                                    Game.player3.shiftCommand = false;
                                    Game.player3.goCommand = false;
                                }
                                break;
                        
                        case 4: if ((Game.player4.turnCommand) && (Game.player4.shiftCommand) && (Game.player4.goCommand)){
                                    game.currentPlayer = 1;
                                    Game.player4.turnCommand = false;
                                    Game.player4.shiftCommand = false;
                                    Game.player4.goCommand = false;
                                }
                                break;
                    }
                    break;
        }
        
        refresh();
    }
    
    public void storeTurnCommand(){
        switch(game.currentPlayer){
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
        switch(game.currentPlayer){
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
        switch(game.currentPlayer){
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
    
    public void refresh(){
        //gameboard.panelGame = gameboard.panelGameBoard = gameboard.panelGameHeader = null;
        gameboard.printHeader(game.currentPlayer);
        switch(game.currentPlayer){
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
