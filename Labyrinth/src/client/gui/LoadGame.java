/*
 * IJA 2015: The Labyrinth
 */
package client.gui;

import client.board.MazeBoard;
import client.board.MazeCard;
import client.board.MazeField;
import client.client;
import static client.client.gui;
import client.game.Game;
import static client.game.Game.boardSize;
import static client.game.Game.mazeboard;
import static client.game.Game.numberOfPlayers;
import static client.game.Game.numberOfTreasures;
import static client.game.Game.player1;
import static client.game.Game.player2;
import static client.game.Game.player3;
import static client.game.Game.player4;
import static client.gui.Gui.gameboard;
import client.treasure.Treasure;
import client.undo.undo;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author xpospi73, xdress00
 */
public class LoadGame implements Serializable{
    public JButton buttonLoadGameBack = new JButton("Back");
    public JButton buttonLoadGame1 = new JButton("Load Game 1");
    
    private final JLabel labelCreditsTitle = new JLabel("Load Game");
    
    public JPanel panelLoadGameTitle = new JPanel();
    public JPanel panelLoadGameContent = new JPanel();
    
    private final BoxLayout boxLayoutLoadGame = new BoxLayout(panelLoadGameContent, BoxLayout.Y_AXIS);
    
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
        buttonLoadGame1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    FileInputStream saveFile = new FileInputStream(Gui.path + "/examples/SAVE_2015_05_06_02_00_06.sav");
                    ObjectInputStream restore = new ObjectInputStream(saveFile);
                    Gui.game = (Game) restore.readObject();
                    Game.currentPlayer = (int) restore.readObject();
                    Game.numberOfPlayers = (int) restore.readObject();
                    Game.numberOfTreasures = (int) restore.readObject();
                    Game.boardSize = (int) restore.readObject();
                    Game.pack = (java.util.List<Treasure>) restore.readObject();
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
        }); 
        
        panelLoadGameTitle.add(labelCreditsTitle);
        panelLoadGameTitle.add(Box.createRigidArea(new Dimension(0,50)));
        panelLoadGameContent.add(Box.createRigidArea(new Dimension(0,20)));
        panelLoadGameContent.add(buttonLoadGame1);
        panelLoadGameContent.add(Box.createRigidArea(new Dimension(0,20)));
        panelLoadGameContent.add(buttonLoadGameBack);
        
        panelLoadGameContent.setLayout(boxLayoutLoadGame);
    }
}
