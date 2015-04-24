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
    
    String path = System.getProperty("user.dir");
    
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
        else {
            boardSize = 11;
        }
        
        if (newgame.radioButtonNewGameNumberOfPlayers2.isSelected()){
            numberOfPlayers = 2;
        }
        else if (newgame.radioButtonNewGameNumberOfPlayers4.isSelected()){
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
        game = new Game(boardSize, numberOfPlayers, numberOfTreasures);
        setGame();
    }
    
    public void setGame() {
        gameboard = new GameBoard();
        
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = gbc.weighty = 1.0;
        gui.add(gameboard.panelNewGameTitle, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gui.add(gameboard.panelNewGameControls, gbc);
        validate();
        repaint();
    }
}
