/*
 * IJA 2015: The Labyrinth
 */
package client.gui;

import client.board.MazeBoard;
import client.board.MazeCard;
import client.board.MazeField;
import static client.client.gui;
import client.game.Game;
import client.treasure.Treasure;
import client.undo.undo;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author xpospi73, xdress00
 */

/**
 *  This class represents window for loading games
 */
public class LoadGame implements Serializable{
    public JButton buttonLoadGameBack = new JButton("Back");
    public JButton buttonLoadGame1 = new JButton();
    public JButton buttonLoadGame2 = new JButton();
    public JButton buttonLoadGame3 = new JButton();
    public JButton buttonLoadGame4 = new JButton();
    public JButton buttonLoadGame5 = new JButton();
    
    private final JLabel labelCreditsTitle = new JLabel("Load Game");
    
    public JPanel panelLoadGameTitle = new JPanel();
    public JPanel panelLoadGameContent = new JPanel();
    
    private final BoxLayout boxLayoutLoadGame = new BoxLayout(panelLoadGameContent, BoxLayout.Y_AXIS);
    
    /**
     * Constructor for initialize components and actions
     */
    public LoadGame() {
        panelLoadGameTitle.setBorder(new EmptyBorder(50,50,50,50));
        panelLoadGameTitle.setBackground(new Color(0,0,0, (float) 0.5));
        panelLoadGameContent.setBackground(new Color(0,0,0, (float) 0.5));
        
        labelCreditsTitle.setFont(new Font("Calibri", Font.PLAIN, 50));
        labelCreditsTitle.setForeground(Color.WHITE);
        labelCreditsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
                
        buttonLoadGameBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonLoadGameBack.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonLoadGameBack.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.remove(panelLoadGameTitle);
                gui.remove(panelLoadGameContent);
                gui.setMenu();
            }
        });
        
        buttonLoadGame1.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonLoadGame1.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonLoadGame1.setText("Saved Game 1.");
        buttonLoadGame1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                File folder = new File(Gui.path + "/examples/");
                File[] listOfFiles = folder.listFiles();
                LoadGame(listOfFiles[0].getName());
            }
        }); 
        
        buttonLoadGame2.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonLoadGame2.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonLoadGame2.setText("Saved Game 2.");
        buttonLoadGame2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                File folder = new File(Gui.path + "/examples/");
                File[] listOfFiles = folder.listFiles();
                LoadGame(listOfFiles[1].getName());
            }
        }); 
        
        buttonLoadGame3.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonLoadGame3.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonLoadGame3.setText("Saved Game 3.");
        buttonLoadGame3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                File folder = new File(Gui.path + "/examples/");
                File[] listOfFiles = folder.listFiles();
                LoadGame(listOfFiles[2].getName());
            }
        }); 
        
        buttonLoadGame4.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonLoadGame4.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonLoadGame4.setText("Saved Game 4.");
        buttonLoadGame4.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                File folder = new File(Gui.path + "/examples/");
                File[] listOfFiles = folder.listFiles();
                LoadGame(listOfFiles[3].getName());
            }
        }); 
        
        buttonLoadGame5.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonLoadGame5.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonLoadGame5.setText("Saved Game 5.");
        buttonLoadGame5.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                File folder = new File(Gui.path + "/examples/");
                File[] listOfFiles = folder.listFiles();
                LoadGame(listOfFiles[4].getName());
            }
        }); 
        
        panelLoadGameTitle.add(labelCreditsTitle);
        panelLoadGameTitle.add(Box.createRigidArea(new Dimension(0,50)));
        panelLoadGameContent.add(Box.createRigidArea(new Dimension(0,20)));
        panelLoadGameContent.add(buttonLoadGame1);
        panelLoadGameContent.add(Box.createRigidArea(new Dimension(0,10)));
        panelLoadGameContent.add(buttonLoadGame2);
        panelLoadGameContent.add(Box.createRigidArea(new Dimension(0,10)));
        panelLoadGameContent.add(buttonLoadGame3);
        panelLoadGameContent.add(Box.createRigidArea(new Dimension(0,10)));
        panelLoadGameContent.add(buttonLoadGame4);
        panelLoadGameContent.add(Box.createRigidArea(new Dimension(0,10)));
        panelLoadGameContent.add(buttonLoadGame5);
        panelLoadGameContent.add(Box.createRigidArea(new Dimension(0,20)));
        panelLoadGameContent.add(buttonLoadGameBack);
        
        panelLoadGameContent.setLayout(boxLayoutLoadGame);
    }
    
    /**
     * Load specified game from pathToSave
     * @param pathToSave Path of saved game
     */
    public void LoadGame(String pathToSave){
        try{
                    FileInputStream saveFile = new FileInputStream(Gui.path + "/examples/" + pathToSave);
                    ObjectInputStream restore = new ObjectInputStream(saveFile);
                    Gui.game = (Game) restore.readObject();
                    Game.currentPlayer = (int) restore.readObject();
                    Game.numberOfPlayers = (int) restore.readObject();
                    Game.numberOfTreasures = (int) restore.readObject();
                    Game.boardSize = (int) restore.readObject();
                    Game.treasuresPositions = (int[][]) restore.readObject();
                    Gui.gameboard = (GameBoard) restore.readObject();
                    Game.player1 = (Player) restore.readObject();
                    Game.player2 = (Player) restore.readObject();
                    Game.player3 = (Player) restore.readObject();
                    Game.player4 = (Player) restore.readObject();
                    Game.mazeboard = (MazeBoard) restore.readObject();
                    MazeBoard.field = (MazeField[][]) restore.readObject();
                    MazeBoard.freeCard = (MazeCard) restore.readObject();
                    MazeBoard.size = (int) restore.readObject();
                    Game.field = (MazeField) restore.readObject();
                    Treasure.cards = (int) restore.readObject();
                    Game.player1.undo = (undo) restore.readObject();
                    Game.player1.assignedTreasure = (Treasure) restore.readObject();
                    Game.player2.undo = (undo) restore.readObject();
                    Game.player2.assignedTreasure = (Treasure) restore.readObject();
                    if(Game.numberOfPlayers == 3) {
                        Game.player3.undo = (undo) restore.readObject();
                        Game.player3.assignedTreasure = (Treasure) restore.readObject();
                    }
                    else if(Game.numberOfPlayers == 4) {
                        Game.player3.undo = (undo) restore.readObject();
                        Game.player3.assignedTreasure = (Treasure) restore.readObject();
                        Game.player4.undo = (undo) restore.readObject();
                        Game.player4.assignedTreasure = (Treasure) restore.readObject();
                    }
                    
                    int packSize = (int) restore.readObject();
                    Game.pack = new ArrayList<>();
                    for(int i = 0; i < packSize; i++){
                        Game.pack.add((Treasure) restore.readObject());
                    }
                    
                    restore.close();
                    
                    Gui.gameboard.buttonGameMenu.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gui.remove(Gui.gameboard.panelGameHeader);
                            gui.remove(Gui.gameboard.panelGame);
                            gui.setMenu();
                        }
                    });
                    
                    Gui.gameboard.buttonGameSave.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Game.saveGame();
                        }
                    });
                    
                    Gui.gameboard.buttonGameUndo.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gui.mainPlayingCycle("Undo");
                        }
                    });
                    
                    Gui.gameboard.buttonGameTurnRightCard.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gui.mainPlayingCycle("TurnRightCard");
                        }
                    });
                    
                    Gui.gameboard.buttonGameTurnLeftCard.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gui.mainPlayingCycle("TurnLeftCard");
                        }
                    });
                    
                    Gui.gameboard.buttonGameTurnRightFreeCard.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gui.mainPlayingCycle("TurnRightFreeCard");
                        }
                    });
                    
                    Gui.gameboard.buttonGameTurnLeftFreeCard.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gui.mainPlayingCycle("TurnLeftFreeCard");
                        }
                    });
                    
                    Gui.gameboard.buttonGameShiftRight.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gui.mainPlayingCycle("ShiftRight");
                        }
                    });
                    
                    Gui.gameboard.buttonGameShiftLeft.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gui.mainPlayingCycle("ShiftLeft");
                        }
                    });
                    
                    Gui.gameboard.buttonGameShiftDown.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gui.mainPlayingCycle("ShiftDown");
                        }
                    });
                    
                    Gui.gameboard.buttonGameShiftUp.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gui.mainPlayingCycle("ShiftUp");
                        }
                    });
                    
                    Gui.gameboard.buttonGameGo.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gui.mainPlayingCycle("Go");
                        }
                    });
                    
                    gui.remove(panelLoadGameTitle);
                    gui.remove(panelLoadGameContent);
                    gui.mainPlayingCycle("Start");
                }
                catch(Exception exc){
                    exc.printStackTrace();
                }
    }
}
