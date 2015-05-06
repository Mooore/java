/*
 * IJA 2015: The Labyrinth
 */
package client.gui;

import client.board.MazeCard;
import static client.client.gui;
import client.game.*;
import static client.gui.Gui.path;
import client.treasure.Treasure;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author xpospi73, xdress00
 */

/**
 * This class represents a game board.
 */
public class GameBoard implements Serializable {
    public JButton buttonGameMenu = new JButton("Menu");
    public JButton buttonGameSave = new JButton("Save");
    public JButton buttonGameUndo = new JButton("Undo");
    public JButton buttonGameTurnRightCard = new JButton("Turn right card");
    public JButton buttonGameTurnLeftCard = new JButton("Turn left card");
    public JButton buttonGameTurnRightFreeCard = new JButton("Turn right free card");
    public JButton buttonGameTurnLeftFreeCard = new JButton("Turn left free card");
    public JButton buttonGameShiftRight = new JButton("Shift right");
    public JButton buttonGameShiftLeft = new JButton("Shift left");
    public JButton buttonGameShiftDown = new JButton("Shift down");
    public JButton buttonGameShiftUp = new JButton("Shift up");
    public JButton buttonGameGo = new JButton("Go");
    
    public final JLabel labelGamePlaying = new JLabel("Playing: ");
    public final JLabel labelGamePlayer1 = new JLabel("Player 1");
    public final JLabel labelGamePlayer2 = new JLabel("Player 2");
    public final JLabel labelGamePlayer3 = new JLabel("Player 3");
    public final JLabel labelGamePlayer4 = new JLabel("Player 4");
    public final JLabel labelGameObtainedTreasures = new JLabel("Obtained treasures: ");
    public final JLabel labelGameHistory = new JLabel("History");
    public final JLabel labelGameControls = new JLabel("Controls");
    public final JLabel labelGameTreasure = new JLabel("Treasure");
    public final JLabel labelGameFreeCard = new JLabel("Free card");
    public JLabel labelGameHistory1 = new JLabel("");
    public JLabel labelGameHistory2 = new JLabel("");
    public JLabel labelGameHistory3 = new JLabel("");
    public JLabel labelGameHistory4 = new JLabel("");
    public JLabel labelGameHistoryPlayer1 = new JLabel("");
    public JLabel labelGameHistoryPlayer2 = new JLabel("");
    public JLabel labelGameHistoryPlayer3 = new JLabel("");
    public JLabel labelGameHistoryPlayer4 = new JLabel("");
    public JLabel labelGameTreasureCoordinates = new JLabel("");
    
    public JTextField textFieldGameTurnRightCardX = new JTextField("1");
    public JTextField textFieldGameTurnRightCardY = new JTextField("1");
    public JTextField textFieldGameTurnLeftCardX = new JTextField("1");
    public JTextField textFieldGameTurnLeftCardY = new JTextField("1");
    public JTextField textFieldGameShiftRight = new JTextField("2");
    public JTextField textFieldGameShiftLeft = new JTextField("2");
    public JTextField textFieldGameShiftDown = new JTextField("2");
    public JTextField textFieldGameShiftUp = new JTextField("2");
    public JTextField textFieldGameGoX = new JTextField("1");
    public JTextField textFieldGameGoY = new JTextField("1");
    
    public JPanel panelGameHeader = new JPanel();
    public JPanel panelGame = new JPanel(new BorderLayout());
    public JPanel panelGameBoard = new JPanel();
    public JPanel panelGameControls = new JPanel();
    public JPanel panelGameHistory = new JPanel();
    public JPanel panelGameHistoryField = new JPanel();
    public JPanel panelGameControlsButtons = new JPanel();
    public JPanel panelGameControlsButtonsLeft = new JPanel();
    public JPanel panelGameControlsButtonsLeftInner = new JPanel();
    public JPanel panelGameControlsButtonsRight = new JPanel();
    public JPanel panelGameControlsButtonsRightInner = new JPanel();
    public JPanel panelGameControlsTitle = new JPanel();
    public JPanel panelGameControlsTurnRight = new JPanel();
    public JPanel panelGameControlsTurnLeft = new JPanel();
    public JPanel panelGameControlsTurnRightFreeCard = new JPanel();
    public JPanel panelGameControlsTurnLeftFreeCard = new JPanel();
    public JPanel panelGameControlsShiftRight = new JPanel();
    public JPanel panelGameControlsShiftLeft = new JPanel();
    public JPanel panelGameControlsShiftDown = new JPanel();
    public JPanel panelGameControlsShiftUp = new JPanel();
    public JPanel panelGameControlsGo = new JPanel();
    public JPanel panelGameTreasureFreeCard = new JPanel();
    public JPanel panelGameTreasure = new JPanel();
    public JPanel panelGameFreeCard = new JPanel();
    
    private final BoxLayout boxLayoutGameControlsButtonsLeftInner = new BoxLayout(panelGameControlsButtonsLeftInner, BoxLayout.Y_AXIS);
    private final BoxLayout boxLayoutGameControlsButtonsRightInner = new BoxLayout(panelGameControlsButtonsRightInner, BoxLayout.Y_AXIS);
    
    private String player1_history, player2_history, player3_history, player4_history;
    private String player1Text, player2Text, player3Text ,player4Text;
    private boolean cleanCommandHistory1, cleanCommandHistory2, cleanCommandHistory3, cleanCommandHistory4;
    
    /**
     * Constructor inicializes the game board.
     */
    public GameBoard(){
        
        player1_history = "";
        player2_history = "";
        player3_history = "";
        player4_history = "";
        
        player1Text = "";
        player2Text = "";
        player3Text = "";
        player4Text = "";
        
        cleanCommandHistory1 = false;
        cleanCommandHistory2 = false;
        cleanCommandHistory3 = false;
        cleanCommandHistory4 = false;
        
        buttonGameMenu.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonGameMenu.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.remove(panelGameHeader);
                gui.remove(panelGame);
                gui.setMenu();
            }
        });
                
        buttonGameSave.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonGameSave.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Game.saveGame();
            }
        });
                
        buttonGameUndo.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonGameUndo.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.mainPlayingCycle("Undo");
            }
        });
        
        labelGamePlaying.setFont(new Font("Calibri", Font.BOLD, 20));
        labelGamePlaying.setForeground(Color.WHITE);
        
        labelGamePlayer1.setFont(new Font("Calibri", Font.BOLD, 20));
        labelGamePlayer1.setForeground(Game.player1.getPlayerColor());
        
        labelGamePlayer2.setFont(new Font("Calibri", Font.BOLD, 20));
        labelGamePlayer2.setForeground(Game.player2.getPlayerColor());
              
        if(Game.numberOfPlayers > 2){
            labelGamePlayer3.setFont(new Font("Calibri", Font.BOLD, 20));
            labelGamePlayer3.setForeground(Game.player3.getPlayerColor());
        }
        if(Game.numberOfPlayers > 3){
            labelGamePlayer4.setFont(new Font("Calibri", Font.BOLD, 20));
            labelGamePlayer4.setForeground(Game.player4.getPlayerColor());
        }
        
        labelGameObtainedTreasures.setFont(new Font("Calibri", Font.BOLD, 20));
        labelGameObtainedTreasures.setForeground(Color.WHITE);       
        
        /*************************************************************************/
        
        panelGameControlsButtonsLeft.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsButtonsLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.WHITE));
        panelGameControlsButtonsLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelGameControlsButtonsLeftInner.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsButtonsRightInner.setBackground(new Color(0,0,0, (float) 0.0));
        
        panelGameControlsButtonsRight.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsButtonsRight.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        panelGameControlsButtonsRight.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelGameControlsTurnRight.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsTurnLeft.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsTurnRightFreeCard.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsTurnLeftFreeCard.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsShiftRight.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsShiftLeft.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsShiftDown.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsShiftUp.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsGo.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsTitle.setBackground(new Color(0,0,0, (float) 0.0));
                        
        panelGameControlsButtons.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsButtons.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.WHITE));
        
        panelGameControlsButtons.setLayout(new GridLayout(1,2));
        panelGameControlsButtons.add(panelGameControlsButtonsLeft, BorderLayout.WEST);
        panelGameControlsButtons.add(panelGameControlsButtonsRight, BorderLayout.EAST);
        
        panelGameControlsButtonsLeft.add(panelGameControlsButtonsLeftInner);
        panelGameControlsButtonsRight.add(panelGameControlsButtonsRightInner);
        
        /*************************************************************************/
        
        labelGameHistory.setFont(new Font("Calibri", Font.BOLD, 20));
        labelGameHistory.setForeground(Color.WHITE);
        labelGameHistory.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        labelGameControls.setFont(new Font("Calibri", Font.BOLD, 20));
        labelGameControls.setForeground(Color.WHITE);
        labelGameControls.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        labelGameTreasure.setFont(new Font("Calibri", Font.BOLD, 15));
        labelGameTreasure.setForeground(Color.WHITE);
        labelGameTreasure.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelGameTreasure.setBorder(new EmptyBorder(10,10,10,10));
        
        labelGameFreeCard.setFont(new Font("Calibri", Font.BOLD, 15));
        labelGameFreeCard.setForeground(Color.WHITE);
        labelGameFreeCard.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelGameFreeCard.setBorder(new EmptyBorder(10,10,10,10));
        
        buttonGameTurnRightCard.setFont(new Font("Calibri", Font.BOLD, 13));
        buttonGameTurnRightCard.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonGameTurnRightCard.setMargin(new Insets(1,1,1,1));
        buttonGameTurnRightCard.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.mainPlayingCycle("TurnRightCard");
            }
        });
        
        buttonGameTurnLeftCard.setFont(new Font("Calibri", Font.BOLD, 13));
        buttonGameTurnLeftCard.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonGameTurnLeftCard.setMargin(new Insets(1,1,1,1));
        buttonGameTurnLeftCard.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.mainPlayingCycle("TurnLeftCard");
            }
        });
        
        buttonGameTurnRightFreeCard.setFont(new Font("Calibri", Font.BOLD, 13));
        buttonGameTurnRightFreeCard.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonGameTurnRightFreeCard.setMargin(new Insets(1,1,1,1));
        buttonGameTurnRightFreeCard.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.mainPlayingCycle("TurnRightFreeCard");
            }
        });
        
        buttonGameTurnLeftFreeCard.setFont(new Font("Calibri", Font.BOLD, 13));
        buttonGameTurnLeftFreeCard.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonGameTurnLeftFreeCard.setMargin(new Insets(1,1,1,1));
        buttonGameTurnLeftFreeCard.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.mainPlayingCycle("TurnLeftFreeCard");
            }
        });
        
        buttonGameShiftRight.setFont(new Font("Calibri", Font.BOLD, 13));
        buttonGameShiftRight.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonGameShiftRight.setMargin(new Insets(1,1,1,1));
        buttonGameShiftRight.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.mainPlayingCycle("ShiftRight");
            }
        });
        
        buttonGameShiftLeft.setFont(new Font("Calibri", Font.BOLD, 13));
        buttonGameShiftLeft.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonGameShiftLeft.setMargin(new Insets(1,1,1,1));
        buttonGameShiftLeft.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.mainPlayingCycle("ShiftLeft");
            }
        });
        
        buttonGameShiftDown.setFont(new Font("Calibri", Font.BOLD, 13));
        buttonGameShiftDown.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonGameShiftDown.setMargin(new Insets(1,1,1,1));
        buttonGameShiftDown.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.mainPlayingCycle("ShiftDown");
            }
        });
        
        buttonGameShiftUp.setFont(new Font("Calibri", Font.BOLD, 13));
        buttonGameShiftUp.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonGameShiftUp.setMargin(new Insets(1,1,1,1));
        buttonGameShiftUp.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.mainPlayingCycle("ShiftUp");
            }
        });
        
        buttonGameGo.setFont(new Font("Calibri", Font.BOLD, 15));
        buttonGameGo.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonGameGo.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.mainPlayingCycle("Go");
            }
        });
        
        textFieldGameTurnRightCardX.setColumns(2);
        textFieldGameTurnRightCardY.setColumns(2);
        textFieldGameTurnLeftCardX.setColumns(2);
        textFieldGameTurnLeftCardY.setColumns(2);
        textFieldGameShiftRight.setColumns(2);
        textFieldGameShiftLeft.setColumns(2);
        textFieldGameShiftDown.setColumns(2);
        textFieldGameShiftUp.setColumns(2);
        textFieldGameGoX.setColumns(2);
        textFieldGameGoY.setColumns(2);
                                
        panelGameControlsTitle.add(labelGameControls);
        panelGameControlsTurnRight.add(buttonGameTurnRightCard);
        panelGameControlsTurnRight.add(textFieldGameTurnRightCardX);
        panelGameControlsTurnRight.add(textFieldGameTurnRightCardY);
        panelGameControlsTurnLeft.add(buttonGameTurnLeftCard);
        panelGameControlsTurnLeft.add(textFieldGameTurnLeftCardX);
        panelGameControlsTurnLeft.add(textFieldGameTurnLeftCardY);
        panelGameControlsTurnRightFreeCard.add(buttonGameTurnRightFreeCard);
        panelGameControlsTurnLeftFreeCard.add(buttonGameTurnLeftFreeCard);
        panelGameControlsShiftRight.add(buttonGameShiftRight);
        panelGameControlsShiftRight.add(textFieldGameShiftRight);
        panelGameControlsShiftLeft.add(buttonGameShiftLeft);
        panelGameControlsShiftLeft.add(textFieldGameShiftLeft);
        panelGameControlsShiftDown.add(buttonGameShiftDown);
        panelGameControlsShiftDown.add(textFieldGameShiftDown);
        panelGameControlsShiftUp.add(buttonGameShiftUp);
        panelGameControlsShiftUp.add(textFieldGameShiftUp);
        panelGameControlsGo.add(buttonGameGo);
        panelGameControlsGo.add(textFieldGameGoX);
        panelGameControlsGo.add(textFieldGameGoY);
        
        panelGameControlsButtonsLeftInner.add(panelGameControlsTitle);
        panelGameControlsButtonsLeftInner.add(panelGameControlsTurnRight);
        panelGameControlsButtonsLeftInner.add(panelGameControlsTurnLeft);
        panelGameControlsButtonsLeftInner.add(panelGameControlsTurnRightFreeCard);
        panelGameControlsButtonsLeftInner.add(panelGameControlsTurnLeftFreeCard);
        panelGameControlsButtonsRightInner.add(panelGameControlsShiftRight);
        panelGameControlsButtonsRightInner.add(panelGameControlsShiftLeft);
        panelGameControlsButtonsRightInner.add(panelGameControlsShiftDown);
        panelGameControlsButtonsRightInner.add(panelGameControlsShiftUp);
        panelGameControlsButtonsRightInner.add(panelGameControlsGo);
        
        panelGameControlsButtonsLeftInner.setLayout(boxLayoutGameControlsButtonsLeftInner);
        panelGameControlsButtonsRightInner.setLayout(boxLayoutGameControlsButtonsRightInner);
    }
    
    /**
     * This method prints the header of current player.
     * @param currentPlayer represents a number of current player.
     */
    void printHeader(int currentPlayer){
        panelGameHeader = new JPanel();
        panelGameHeader.setBackground(new Color(0,0,0, (float) 0.7));
        panelGameHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.WHITE));
        
        panelGameHeader.add(buttonGameMenu);
        panelGameHeader.add(Box.createRigidArea(new Dimension(10,0)));
        panelGameHeader.add(buttonGameSave);
        panelGameHeader.add(Box.createRigidArea(new Dimension(10,0)));
        panelGameHeader.add(buttonGameUndo);
        panelGameHeader.add(Box.createRigidArea(new Dimension(170,0)));
        panelGameHeader.add(labelGamePlaying);
        panelGameHeader.add(Box.createRigidArea(new Dimension(10,0)));
        switch(currentPlayer){
            case 1: panelGameHeader.add(labelGamePlayer1);
                    break;
            case 2: panelGameHeader.add(labelGamePlayer2);
                    break;
            case 3: panelGameHeader.add(labelGamePlayer3);
                    break;
            case 4: panelGameHeader.add(labelGamePlayer4);
                    break;
        }
        panelGameHeader.add(Box.createRigidArea(new Dimension(60,0)));
        panelGameHeader.add(labelGameObtainedTreasures);
    }
   
    /**
     * This method prints the game board.
     */
    public void printGameBoard(){
        panelGameBoard = new JPanel();
        panelGameBoard.setBackground(new Color(0,0,0, (float) 0.0));
        BoxLayout boxLayoutGameBoard = new BoxLayout(panelGameBoard, BoxLayout.Y_AXIS);
        panelGameBoard.setLayout(boxLayoutGameBoard);
    }
    
    /**
     * This method prints the game.
     */
    public void printGame(){
        panelGame = new JPanel(new BorderLayout());
        panelGame.setBackground(new Color(0,0,0, (float) 0.5));
        panelGame.add(panelGameBoard, BorderLayout.WEST);
        panelGame.add(panelGameControls, BorderLayout.EAST);
    }
    
    /**
     * This method prints the player's obtained treasures.
     * @param player current player.
     */
    public void printObtainedTreasures(Player player){
        JLabel labelObtained = new JLabel(Integer.toString(player.getObtainedTreasures()));
        labelObtained.setFont(new Font("Calibri", Font.BOLD, 20));
        labelObtained.setForeground(Color.WHITE);
        panelGameHeader.add(Box.createRigidArea(new Dimension(10,0)));
        panelGameHeader.add(labelObtained);
    }
    
    /**
     * This method prints the free card.
     */
    public void printFreeCard(){
        panelGameFreeCard = new JPanel();
        panelGameFreeCard.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameFreeCard.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        panelGameFreeCard.add(labelGameFreeCard);
        
        JLabel free = printCard(Game.mazeboard.getFreeCard());
        free.setAlignmentX(Component.CENTER_ALIGNMENT);
        free.setBorder(new EmptyBorder(10,10,10,10));
                
        panelGameFreeCard.add(free);
        
        BoxLayout boxLayoutGameFreeCard = new BoxLayout(panelGameFreeCard, BoxLayout.Y_AXIS);
        panelGameFreeCard.setLayout(boxLayoutGameFreeCard);
    }
    
    /**
     * This method prints treasures.
     */
    public void printTreasure(){
        panelGameTreasure = new JPanel();
        panelGameTreasure.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameTreasure.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.WHITE));
        
        JLabel treasure = new JLabel();
        int picture, x = 0, y = 0;
        
        switch(Game.numberOfPlayers){
            case 2: switch(Game.currentPlayer){
                        case 1: picture = Game.player1.getAssignedTreasure().picture;
                                treasure = printTreasure(picture);
                                x = Game.treasuresPositions[0][Game.player1.getAssignedTreasure().code];
                                y = Game.treasuresPositions[1][Game.player1.getAssignedTreasure().code];
                                labelGameTreasureCoordinates = new JLabel("Positions: [" + x + ":" + y + "]");
                                labelGameTreasureCoordinates.setForeground(Color.GREEN);
                                break;
                        case 2: picture = Game.player2.getAssignedTreasure().picture;
                                treasure = printTreasure(picture);
                                x = Game.treasuresPositions[0][Game.player2.getAssignedTreasure().code];
                                y = Game.treasuresPositions[1][Game.player2.getAssignedTreasure().code];
                                labelGameTreasureCoordinates = new JLabel("Positions: [" + x + ":" + y + "]");
                                labelGameTreasureCoordinates.setForeground(Color.RED);
                                break;
                    }
                    break;
            case 3: switch(Game.currentPlayer){
                        case 1: picture = Game.player1.getAssignedTreasure().picture;
                                treasure = printTreasure(picture);
                                x = Game.treasuresPositions[0][Game.player1.getAssignedTreasure().code];
                                y = Game.treasuresPositions[1][Game.player1.getAssignedTreasure().code];
                                labelGameTreasureCoordinates = new JLabel("Positions: [" + x + ":" + y + "]");
                                labelGameTreasureCoordinates.setForeground(Color.GREEN);
                                break;
                        case 2: picture = Game.player2.getAssignedTreasure().picture;
                                treasure = printTreasure(picture);
                                x = Game.treasuresPositions[0][Game.player2.getAssignedTreasure().code];
                                y = Game.treasuresPositions[1][Game.player2.getAssignedTreasure().code];
                                labelGameTreasureCoordinates = new JLabel("Positions: [" + x + ":" + y + "]");
                                labelGameTreasureCoordinates.setForeground(Color.RED);
                                break;
                        case 3: picture = Game.player3.getAssignedTreasure().picture;
                                treasure = printTreasure(picture);
                                x = Game.treasuresPositions[0][Game.player3.getAssignedTreasure().code];
                                y = Game.treasuresPositions[1][Game.player3.getAssignedTreasure().code];
                                labelGameTreasureCoordinates = new JLabel("Positions: [" + x + ":" + y + "]");
                                labelGameTreasureCoordinates.setForeground(Color.CYAN);
                                break;
                    }
                    break;
            case 4: switch(Game.currentPlayer){
                        case 1: picture = Game.player1.getAssignedTreasure().picture;
                                treasure = printTreasure(picture);
                                x = Game.treasuresPositions[0][Game.player1.getAssignedTreasure().code];
                                y = Game.treasuresPositions[1][Game.player1.getAssignedTreasure().code];
                                labelGameTreasureCoordinates = new JLabel("Positions: [" + x + ":" + y + "]");
                                labelGameTreasureCoordinates.setForeground(Color.GREEN);
                                break;
                        case 2: picture = Game.player2.getAssignedTreasure().picture;
                                treasure = printTreasure(picture);
                                x = Game.treasuresPositions[0][Game.player2.getAssignedTreasure().code];
                                y = Game.treasuresPositions[1][Game.player2.getAssignedTreasure().code];
                                labelGameTreasureCoordinates = new JLabel("Positions: [" + x + ":" + y + "]");
                                labelGameTreasureCoordinates.setForeground(Color.RED);
                                break;
                        case 3: picture = Game.player3.getAssignedTreasure().picture;
                                treasure = printTreasure(picture);
                                x = Game.treasuresPositions[0][Game.player3.getAssignedTreasure().code];
                                y = Game.treasuresPositions[1][Game.player3.getAssignedTreasure().code];
                                labelGameTreasureCoordinates = new JLabel("Positions: [" + x + ":" + y + "]");
                                labelGameTreasureCoordinates.setForeground(Color.CYAN);
                                break;
                        case 4: picture = Game.player4.getAssignedTreasure().picture;
                                treasure = printTreasure(picture);
                                x = Game.treasuresPositions[0][Game.player4.getAssignedTreasure().code];
                                y = Game.treasuresPositions[1][Game.player4.getAssignedTreasure().code];
                                labelGameTreasureCoordinates = new JLabel("Positions: [" + x + ":" + y + "]");
                                labelGameTreasureCoordinates.setForeground(Color.YELLOW);
                                break;
                    }
                    break;
        }
        treasure.setAlignmentX(Component.CENTER_ALIGNMENT);
        treasure.setBorder(new EmptyBorder(10,10,10,10));
        
        labelGameTreasureCoordinates.setFont(new Font("Calibri", Font.PLAIN, 15));
        labelGameTreasureCoordinates.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelGameTreasure.add(labelGameTreasure);
        panelGameTreasure.add(treasure);
        panelGameTreasure.add(labelGameTreasureCoordinates);
                
        BoxLayout boxLayoutGameTreasure = new BoxLayout(panelGameTreasure, BoxLayout.Y_AXIS);
        panelGameTreasure.setLayout(boxLayoutGameTreasure);
    }
    
    /**
     * This method prints the panels of treasure and free card.
     */
    public void printTreasureFreeCard(){
        panelGameTreasureFreeCard = new JPanel();
        panelGameTreasureFreeCard.setBackground(new Color(0,0,0, (float) 0.0));

        panelGameTreasureFreeCard.setLayout(new GridLayout(1,2));
        panelGameTreasureFreeCard.add(panelGameTreasure, BorderLayout.WEST);
        panelGameTreasureFreeCard.add(panelGameFreeCard, BorderLayout.EAST);
    }
    
    /**
     * This method transforms command to printeble form.
     * @param command command to print.
     * @return transformed command.
     */
    private String commandText(String command) {
        String commandText = "",rc;
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
            commandText = " Turn right card [" + row + " : " + col + "] |";
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
            commandText = " Turn left card [" + row + " : " + col + "] |";
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
            commandText = " Shift [" + row + " : " + col + "] |";
        }
        else if(command.matches("^(tf|(-tf))$") == true) {
            commandText = " Turn right free card |";
        }
        else if(command.matches("^(tlf|(-tlf))$") == true) {
            commandText = " Turn left free card |";
        }
        else if (command.matches("^(go|(-go))([0-9])([0-9])?([:])([0-9])([0-9])?([-])([0-9])([0-9])?([:])([0-9])([0-9])?$") == true) {
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
            
            
            commandText = " Go from [" + FromX + " : " + FromY + "] to [" + goX + " : " + goY + "] |";
        }
        return commandText;
    }
    
    /**
     * This method gets players command history.
     * @param playerNumber number of player.
     * @return command history.
     */
    private String playerHistory(int playerNumber) {
       // String commandHistory = " Player " + playerNumber + ": ";
        String commandHistory = "";
        String command;
        
        if(playerNumber == 1) {
            for(int i = 0; i < Game.player1.undo.lastCommand;i++) {
                command = commandText(Game.player1.undo.commands.get(i));
                commandHistory += command;
            }
        }
        else if(playerNumber == 2) {
            for(int i = 0; i < Game.player2.undo.lastCommand;i++) {
                command = commandText(Game.player2.undo.commands.get(i));
                commandHistory += command;
            }
        }
        else if(playerNumber == 3) {
            for(int i = 0; i < Game.player3.undo.lastCommand;i++) {
                command = commandText(Game.player3.undo.commands.get(i));
                commandHistory += command;
            }
        }
        else if(playerNumber == 4) {
            for(int i = 0; i < Game.player4.undo.lastCommand;i++) {
                command = commandText(Game.player4.undo.commands.get(i));
                commandHistory += command;
            }
        }
       
        return commandHistory;
    }
    
    /**
     * This method prints players histories.
     */
    public void printHistoryField(){
        panelGameHistoryField = new JPanel();
        
        panelGameHistoryField.setBackground(Color.BLACK);
        panelGameHistoryField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panelGameHistoryField.setMinimumSize(new Dimension(340, 200));
        panelGameHistoryField.setMaximumSize(new Dimension(340, 200));
        panelGameHistoryField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        if(Game.numberOfPlayers == 2) {
            if(Game.currentPlayer == 1) {
                if(cleanCommandHistory1) {
                    Game.player1.undo.cleanCommands();
                    cleanCommandHistory1 = false;
                }
                if(cleanCommandHistory2 == false) {
                    cleanCommandHistory2 = true;
                }
                player1Text = " Player 1:";
                player1_history = playerHistory(1);
            }
            else if(Game.currentPlayer == 2) {
                if(cleanCommandHistory2) {
                    Game.player2.undo.cleanCommands();
                    cleanCommandHistory2 = false;
                }
                if(cleanCommandHistory1 == false) {
                    cleanCommandHistory1 = true;
                }
                player2Text = " Player 2:";
                player2_history = playerHistory(2);
            }
        }
        else if(Game.numberOfPlayers == 3) {
            if(Game.currentPlayer == 1) {
                if(cleanCommandHistory1) {
                    Game.player1.undo.cleanCommands();
                    cleanCommandHistory1 = false;
                }
                if(cleanCommandHistory2 == false) {
                    cleanCommandHistory2 = true;
                }
                player1Text = " Player 1:";
                player1_history = playerHistory(1);
            }
            else if(Game.currentPlayer == 2) {
                if(cleanCommandHistory2) {
                    Game.player2.undo.cleanCommands();
                    cleanCommandHistory2 = false;
                }
                if(cleanCommandHistory3 == false) {
                    cleanCommandHistory3 = true;
                }
                player2Text = " Player 2:";
                player2_history = playerHistory(2);
            }
            else if(Game.currentPlayer == 3) {
                if(cleanCommandHistory3) {
                    Game.player3.undo.cleanCommands();
                    cleanCommandHistory3 = false;
                }
                if(cleanCommandHistory1 == false) {
                    cleanCommandHistory1 = true;
                }
                player3Text = " Player 3:";
                player3_history = playerHistory(3);
            }
        }
        else if(Game.numberOfPlayers == 4) {
            if(Game.currentPlayer == 1) {
                if(cleanCommandHistory1) {
                    Game.player1.undo.cleanCommands();
                    cleanCommandHistory1 = false;
                }
                if(cleanCommandHistory2 == false) {
                    cleanCommandHistory2 = true;
                }
                player1Text = " Player 1:";
                player1_history = playerHistory(1);
            }
            else if(Game.currentPlayer == 2) {
                if(cleanCommandHistory2) {
                    Game.player2.undo.cleanCommands();
                    cleanCommandHistory2 = false;
                }
                if(cleanCommandHistory3 == false) {
                    cleanCommandHistory3 = true;
                }
                player2Text = " Player 2:";
                player2_history = playerHistory(2);
            }
            else if(Game.currentPlayer == 3) {
                if(cleanCommandHistory3) {
                    Game.player3.undo.cleanCommands();
                    cleanCommandHistory3 = false;
                }
                if(cleanCommandHistory4 == false) {
                    cleanCommandHistory4 = true;
                }
                player3Text = " Player 3:";
                player3_history = playerHistory(3);
            }
            else if(Game.currentPlayer == 4) {
                if(cleanCommandHistory4) {
                    Game.player4.undo.cleanCommands();
                    cleanCommandHistory4 = false;
                }
                if(cleanCommandHistory1 == false) {
                    cleanCommandHistory1 = true;
                }
                player4Text = " Player 4:";
                player4_history = playerHistory(4);
            }
        }
        labelGameHistoryPlayer1 = new JLabel(player1Text);
        
        labelGameHistoryPlayer1.setForeground(Color.GREEN);
        labelGameHistoryPlayer1.setFont(new Font("Calibri", Font.PLAIN, 12));
        labelGameHistoryPlayer1.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        labelGameHistory1 = new JLabel(player1_history);
        
        labelGameHistory1.setForeground(Color.GREEN);
        labelGameHistory1.setFont(new Font("Calibri", Font.PLAIN, 11));
        labelGameHistory1.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        labelGameHistoryPlayer2 = new JLabel(player2Text);
        
        labelGameHistoryPlayer2.setForeground(Color.RED);
        labelGameHistoryPlayer2.setFont(new Font("Calibri", Font.PLAIN, 12));
        labelGameHistoryPlayer2.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        labelGameHistory2 = new JLabel(player2_history);
        
        labelGameHistory2.setForeground(Color.RED);
        labelGameHistory2.setFont(new Font("Calibri", Font.PLAIN, 11));
        labelGameHistory2.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        labelGameHistoryPlayer3 = new JLabel(player3Text);
        
        labelGameHistoryPlayer3.setForeground(Color.CYAN);
        labelGameHistoryPlayer3.setFont(new Font("Calibri", Font.PLAIN, 12));
        labelGameHistoryPlayer3.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        labelGameHistory3 = new JLabel(player3_history);
        
        labelGameHistory3.setForeground(Color.CYAN);
        labelGameHistory3.setFont(new Font("Calibri", Font.PLAIN, 11));
        labelGameHistory3.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        labelGameHistoryPlayer4 = new JLabel(player4Text);
        
        labelGameHistoryPlayer4.setForeground(Color.YELLOW);
        labelGameHistoryPlayer4.setFont(new Font("Calibri", Font.PLAIN, 12));
        labelGameHistoryPlayer4.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        labelGameHistory4 = new JLabel(player4_history);
        
        labelGameHistory4.setForeground(Color.YELLOW);
        labelGameHistory4.setFont(new Font("Calibri", Font.PLAIN, 11));
        labelGameHistory4.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panelGameHistoryField.add(Box.createRigidArea(new Dimension(0,5)));
        panelGameHistoryField.add(labelGameHistoryPlayer1);
        panelGameHistoryField.add(labelGameHistory1);
        panelGameHistoryField.add(Box.createRigidArea(new Dimension(0,2)));
        panelGameHistoryField.add(labelGameHistoryPlayer2);
        panelGameHistoryField.add(labelGameHistory2);
        panelGameHistoryField.add(Box.createRigidArea(new Dimension(0,2)));
        panelGameHistoryField.add(labelGameHistoryPlayer3);
        panelGameHistoryField.add(labelGameHistory3);
        panelGameHistoryField.add(Box.createRigidArea(new Dimension(0,2)));
        panelGameHistoryField.add(labelGameHistoryPlayer4);
        panelGameHistoryField.add(labelGameHistory4);
        
        BoxLayout boxLayoutGameHistoryField = new BoxLayout(panelGameHistoryField, BoxLayout.Y_AXIS);
        panelGameHistoryField.setLayout(boxLayoutGameHistoryField);
    }
    
    /**
     * This method sets space for history.
     */
    public void printHistory(){
        panelGameHistory = new JPanel();
        
        panelGameHistory.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameHistory.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.WHITE));
        
        panelGameHistory.add(labelGameHistory);
        panelGameHistory.add(Box.createRigidArea(new Dimension(350,10)));
        panelGameHistory.add(panelGameHistoryField);
        panelGameHistory.add(Box.createRigidArea(new Dimension(0,10)));
        
        BoxLayout boxLayoutGameHistory = new BoxLayout(panelGameHistory, BoxLayout.Y_AXIS);
        panelGameHistory.setLayout(boxLayoutGameHistory);
    }
    
    /**
     * This method prints Gui panels.
     */
    public void printControls(){
        panelGameControls = new JPanel();
        panelGameControls.setBackground(new Color(0,0,0, (float) 0.5));
        panelGameControls.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.WHITE));
        
        panelGameControls.setLayout(new GridLayout(3,1));   
        panelGameControls.add(panelGameHistory, BorderLayout.NORTH);
        panelGameControls.add(panelGameControlsButtons, BorderLayout.CENTER);
        panelGameControls.add(panelGameTreasureFreeCard, BorderLayout.SOUTH);
    }
    
    /**
     * This method prints whole game board.
     */
    public void printGameMatrix(){
        JPanel panelBoard = new JPanel();
        panelBoard.setLayout(new GridLayout(Game.boardSize + 2, Game.boardSize + 2));
        panelBoard.setBackground(new Color(0,0,0, (float) 0.0));
        JPanel[][] panel = new JPanel[(Game.boardSize + 2)][(Game.boardSize + 2)];
       
        JPanel blank[] = new JPanel[3];
        blank[0] = new JPanel();
        blank[0].setBackground(new Color(0,0,0, (float) 0.0));
        panelBoard.add(blank[0]);
        
        for (int c = 1; c <= Game.boardSize; c++){
            panel[0][c] = new JPanel();
            panel[0][c].setBackground(new Color(0,0,0, (float) 0.0));
            panel[0][c].setBorder(new EmptyBorder(20,0,0,0));
            JLabel num = new JLabel(Integer.toString(c));
            num.setFont(new Font("Calibri", Font.BOLD, 20));
            num.setForeground(Color.WHITE);
            panel[0][c].add(num);
            panelBoard.add(panel[0][c]);
        }
        
        blank[1] = new JPanel();
        blank[1].setBackground(new Color(0,0,0, (float) 0.0));
        panelBoard.add(blank[1]);
        
        for (int r = 1; r <= Game.boardSize; r++){
            for (int c = 0; c < Game.boardSize + 2; c++) {
                if(c == 0){
                    panel[r][0] = new JPanel();
                    panel[r][0].setBackground(new Color(0,0,0, (float) 0.0));
                    panel[r][0].setBorder(new EmptyBorder(10,10,0,0));
                    JLabel num = new JLabel(Integer.toString(r));
                    num.setFont(new Font("Calibri", Font.BOLD, 20));
                    num.setForeground(Color.WHITE);
                    panel[r][0].add(num);
                    panelBoard.add(panel[r][0]);
                }
                else if(c == Game.boardSize + 1){
                    panel[r][Game.boardSize + 1] = new JPanel();
                    panel[r][Game.boardSize + 1].setBackground(new Color(0,0,0, (float) 0.0));
                    panel[r][Game.boardSize + 1].setBorder(new EmptyBorder(10,0,0,10));
                    JLabel num = new JLabel(Integer.toString(r));
                    num.setFont(new Font("Calibri", Font.BOLD, 20));
                    num.setForeground(Color.WHITE);
                    panel[r][Game.boardSize + 1].add(num);
                    panelBoard.add(panel[r][Game.boardSize + 1]);
                }
                else {
                    panel[r][c] = new BgPanel(Game.mazeboard.get(r, c).getCard());
                    panel[r][c].setPreferredSize(new Dimension(40,40));
                   
                    if(Game.numberOfPlayers == 4) {
                        if((r == Game.player1.positionR) && (c == Game.player1.positionC)){
                            if((r == Game.player2.positionR) && (c == Game.player2.positionC)){
                                if((r == Game.player3.positionR) && (c == Game.player3.positionC)){
                                    if((r == Game.player4.positionR) && (c == Game.player4.positionC)){
                                        panel[r][c].add(printPlayer(1234));
                                    }
                                    else {
                                        panel[r][c].add(printPlayer(123));
                                    }
                                }
                                if((r == Game.player4.positionR) && (c == Game.player4.positionC)){
                                    panel[r][c].add(printPlayer(124));
                                }
                                else {
                                    panel[r][c].add(printPlayer(12));
                                }
                            }
                            else if((r == Game.player3.positionR) && (c == Game.player3.positionC)){
                                if((r == Game.player4.positionR) && (c == Game.player4.positionC)){
                                    panel[r][c].add(printPlayer(134));
                                }
                                else {
                                    panel[r][c].add(printPlayer(13));
                                }
                            }
                            else if((r == Game.player4.positionR) && (c == Game.player4.positionC)){
                                panel[r][c].add(printPlayer(14));
                            }
                            else {
                                panel[r][c].add(printPlayer(1));
                            }
                        }
                        else if((r == Game.player2.positionR) && (c == Game.player2.positionC)){
                            if((r == Game.player3.positionR) && (c == Game.player3.positionC)){
                                if((r == Game.player4.positionR) && (c == Game.player4.positionC)){
                                    panel[r][c].add(printPlayer(234));
                                }
                                else {
                                    panel[r][c].add(printPlayer(23));
                                }
                            }
                            else if((r == Game.player4.positionR) && (c == Game.player4.positionC)){
                                panel[r][c].add(printPlayer(24));
                            }
                            else {
                                panel[r][c].add(printPlayer(2));
                            }
                        }
                        else if((r == Game.player3.positionR) && (c == Game.player3.positionC)){
                            panel[r][c].add(printPlayer(3));
                        }
                        else if((r == Game.player4.positionR) && (c == Game.player4.positionC)){
                            panel[r][c].add(printPlayer(4));
                        }
                        else {
                            for(int i = 1; i <= Game.numberOfPlayers; i++){
                                switch(i){
                                    case 1: if ((r == Game.treasuresPositions[0][Game.player1.getAssignedTreasure().code]) && 
                                                (c == Game.treasuresPositions[1][Game.player1.getAssignedTreasure().code])) {
                                                panel[r][c].add(printTreasure(Game.player1.getAssignedTreasure().picture));
                                                break;
                                            }
                                    case 2: if ((r == Game.treasuresPositions[0][Game.player2.getAssignedTreasure().code]) && 
                                                (c == Game.treasuresPositions[1][Game.player2.getAssignedTreasure().code])) {
                                                panel[r][c].add(printTreasure(Game.player2.getAssignedTreasure().picture));
                                                break;
                                            }
                                    case 3: if ((r == Game.treasuresPositions[0][Game.player3.getAssignedTreasure().code]) && 
                                                (c == Game.treasuresPositions[1][Game.player3.getAssignedTreasure().code])) {
                                                panel[r][c].add(printTreasure(Game.player3.getAssignedTreasure().picture));
                                                break;
                                            }
                                    case 4: if ((r == Game.treasuresPositions[0][Game.player4.getAssignedTreasure().code]) && 
                                                (c == Game.treasuresPositions[1][Game.player4.getAssignedTreasure().code])) {
                                                panel[r][c].add(printTreasure(Game.player4.getAssignedTreasure().picture));
                                                break;
                                            }
                                }
                            }
                            for(int i = 0; i < Treasure.cards; i++){
                                if ((r == Game.treasuresPositions[0][i]) && (c == Game.treasuresPositions[1][i])) {
                                    panel[r][c].add(printTreasure(Game.pack.get(i).picture));
                                    break;
                                }
                            }
                        }
                    }
                    else if(Game.numberOfPlayers == 3) {
                        if((r == Game.player1.positionR) && (c == Game.player1.positionC)){
                            if((r == Game.player2.positionR) && (c == Game.player2.positionC)){
                                if((r == Game.player3.positionR) && (c == Game.player3.positionC)){
                                    panel[r][c].add(printPlayer(123));
                                }
                                else {
                                    panel[r][c].add(printPlayer(12));
                                }
                            }
                            else if((r == Game.player3.positionR) && (c == Game.player3.positionC)){
                                panel[r][c].add(printPlayer(13));
                            }
                            else {
                                panel[r][c].add(printPlayer(1));
                            }
                        }
                        else if((r == Game.player2.positionR) && (c == Game.player2.positionC)){
                            if((r == Game.player3.positionR) && (c == Game.player3.positionC)){
                                panel[r][c].add(printPlayer(23));
                            }
                            else {
                                panel[r][c].add(printPlayer(2));
                            }
                        }
                        else if((r == Game.player3.positionR) && (c == Game.player3.positionC)){
                            panel[r][c].add(printPlayer(3));
                        }
                        else {
                            for(int i = 1; i <= Game.numberOfPlayers; i++){
                                switch(i){
                                    case 1: if ((r == Game.treasuresPositions[0][Game.player1.getAssignedTreasure().code]) && 
                                                (c == Game.treasuresPositions[1][Game.player1.getAssignedTreasure().code])) {
                                                panel[r][c].add(printTreasure(Game.player1.getAssignedTreasure().picture));
                                                break;
                                            }
                                    case 2: if ((r == Game.treasuresPositions[0][Game.player2.getAssignedTreasure().code]) && 
                                                (c == Game.treasuresPositions[1][Game.player2.getAssignedTreasure().code])) {
                                                panel[r][c].add(printTreasure(Game.player2.getAssignedTreasure().picture));
                                                break;
                                            }
                                    case 3: if ((r == Game.treasuresPositions[0][Game.player3.getAssignedTreasure().code]) && 
                                                (c == Game.treasuresPositions[1][Game.player3.getAssignedTreasure().code])) {
                                                panel[r][c].add(printTreasure(Game.player3.getAssignedTreasure().picture));
                                                break;
                                            }
                                }
                            }
                            for(int i = 0; i < Treasure.cards; i++){
                                if ((r == Game.treasuresPositions[0][i]) && (c == Game.treasuresPositions[1][i])) {
                                    panel[r][c].add(printTreasure(Game.pack.get(i).picture));
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        if((r == Game.player1.positionR) && (c == Game.player1.positionC)){
                            if((r == Game.player2.positionR) && (c == Game.player2.positionC)){
                                panel[r][c].add(printPlayer(12));
                            }
                            else {
                                panel[r][c].add(printPlayer(1));
                            }
                        }
                        else if((r == Game.player2.positionR) && (c == Game.player2.positionC)){
                            panel[r][c].add(printPlayer(2));
                            
                        }
                        else {
                            for(int i = 1; i <= Game.numberOfPlayers; i++){
                                switch(i){
                                    case 1: if ((r == Game.treasuresPositions[0][Game.player1.getAssignedTreasure().code]) && 
                                                (c == Game.treasuresPositions[1][Game.player1.getAssignedTreasure().code])) {
                                                panel[r][c].add(printTreasure(Game.player1.getAssignedTreasure().picture));
                                                break;
                                            }
                                    case 2: if ((r == Game.treasuresPositions[0][Game.player2.getAssignedTreasure().code]) && 
                                                (c == Game.treasuresPositions[1][Game.player2.getAssignedTreasure().code])) {
                                                panel[r][c].add(printTreasure(Game.player2.getAssignedTreasure().picture));
                                                break;
                                            }
                                }
                            }
                            for(int i = 0; i < Treasure.cards; i++){
                                if ((r == Game.treasuresPositions[0][i]) && (c == Game.treasuresPositions[1][i])) {
                                    panel[r][c].add(printTreasure(Game.pack.get(i).picture));
                                    break;
                                }
                            }
                        }
                    }
                    
                    panel[r][c].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                    panelBoard.add(panel[r][c]);
                }
            }
        }
        
        blank[2] = new JPanel();
        blank[2].setBackground(new Color(0,0,0, (float) 0.0));
        panelBoard.add(blank[2]);
        
        for (int c = 1; c <= Game.boardSize; c++){
            panel[Game.boardSize + 1][c] = new JPanel();
            panel[Game.boardSize + 1][c].setBackground(new Color(0,0,0, (float) 0.0));
            panel[Game.boardSize + 1][c].setBorder(new EmptyBorder(0,0,20,0));
            JLabel num = new JLabel(Integer.toString(c));
            num.setFont(new Font("Calibri", Font.BOLD, 20));
            num.setForeground(Color.WHITE);
            panel[Game.boardSize + 1][c].add(num);
            panelBoard.add(panel[Game.boardSize + 1][c]);
        }
        
        switch(Game.boardSize){
            case 5:     panelGameBoard.setBorder(new EmptyBorder(150,170,150,0));
                        panelBoard.setPreferredSize(new Dimension(320,200));
                        break;
            case 7:     panelGameBoard.setBorder(new EmptyBorder(100,120,100,0));
                        panelBoard.setPreferredSize(new Dimension(400,300));
                        break;
            case 9:     panelGameBoard.setBorder(new EmptyBorder(50,70,50,0));
                        panelBoard.setPreferredSize(new Dimension(500,400));
                        break;
            case 11:    panelGameBoard.setBorder(new EmptyBorder(0,20,0,0));
                        panelBoard.setPreferredSize(new Dimension(600,500));
                        break;
        }
        panelGameBoard.add(panelBoard);
    }
    
    /**
     * This method prints treasures.
     * @param card represents treasure.
     * @return 
     */
    private static JLabel printTreasure(int card) {
        JLabel label = new JLabel();
        try {
            switch(card){
                case 1: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure1.png"))));
                        break;
                case 2: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure2.png"))));
                        break;
                case 3: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure3.png"))));
                        break;
                case 4: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure4.png"))));
                        break;
                case 5: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure5.png"))));
                        break;
                case 6: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure6.png"))));
                        break;
                case 7: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure7.png"))));
                        break;
                case 8: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure8.png"))));
                        break;
                case 9: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure9.png"))));
                        break;
                case 10:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure10.png"))));
                            break;
                case 11:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure11.png"))));
                            break;
                case 12:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure12.png"))));
                            break;
                case 13:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure13.png"))));
                            break;
                case 14:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure14.png"))));
                            break;
                case 15:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure15.png"))));
                            break;
                case 16:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure16.png"))));
                            break;
                case 17:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure17.png"))));
                            break;
                case 18:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure18.png"))));
                            break;
                case 19:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure19.png"))));
                            break;
                case 20:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure20.png"))));
                            break;
                case 21:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure21.png"))));
                            break;
                case 22:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure22.png"))));
                            break;
                case 23:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure23.png"))));
                            break;
                case 24:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure24.png"))));
                            break;
                case 25:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure25.png"))));
                            break;
                case 26:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure26.png"))));
                            break;
                case 27:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure27.png"))));
                            break;
                case 28:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure28.png"))));
                            break;
                case 29:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure29.png"))));
                            break;
                case 30:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/treasures/treasure30.png"))));
                            break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return label;
    }
    
    /**
     * This method prints the players.
     * @param player represents player.
     * @return 
     */
    private static JLabel printPlayer(int player) {
        JLabel label = new JLabel();
        try {
            switch(player){
                case 1: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/players/player_1.png"))));
                        break;
                case 2: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/players/player_2.png"))));
                        break;
                case 3: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/players/player_3.png"))));
                        break;
                case 4: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/players/player_4.png"))));
                        break;
                case 12: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/players/player_12.png"))));
                        break;
                case 13: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/players/player_13.png"))));
                        break;
                case 14: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/players/player_14.png"))));
                        break;
                case 23: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/players/player_23.png"))));
                        break;
                case 24: label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/players/player_24.png"))));
                        break;
                case 34:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/players/player_34.png"))));
                            break;
                case 123:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/players/player_123.png"))));
                            break;
                case 124:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/players/player_124.png"))));
                            break;
                case 134:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/players/player_134.png"))));
                            break;
                case 234:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/players/player_234.png"))));
                            break;
                case 1234:    label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/players/player_1234.png"))));
                            break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return label;
    }
   
    /**
     * This method prints cards.
     * @param card represents card.
     * @return 
     */
    public JLabel printCard(MazeCard card){
        JLabel label = new JLabel();
        if(card.canGo(MazeCard.CANGO.LEFT) == true) {
            if(card.canGo(MazeCard.CANGO.UP) == false) {
                if(card.canGo(MazeCard.CANGO.RIGHT) == true) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == false) {
                        //System.out.print("═|");
                        try {
                            label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/wayL_true_false_true_false.jpg"))));
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        //System.out.print("╦|");
                        try {
                            label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/wayF_true_false_true_true.jpg"))));
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if(card.canGo(MazeCard.CANGO.RIGHT) == false) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        //System.out.print("╗|");
                        try {
                            label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/wayC_true_false_false_true.jpg"))));
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }           
            else if(card.canGo(MazeCard.CANGO.UP) == true) {
                if(card.canGo(MazeCard.CANGO.RIGHT) == true) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == false) {
                        //System.out.print("╩|");
                        try {
                            label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/wayF_true_true_true_false.jpg"))));
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if(card.canGo(MazeCard.CANGO.RIGHT) == false) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        //System.out.print("╣|");
                        try {
                            label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/wayF_true_true_false_true.jpg"))));
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(card.canGo(MazeCard.CANGO.DOWN) == false) {
                        //System.out.print("╝|");
                        try {
                            label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/wayC_true_true_false_false.jpg"))));
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        else if(card.canGo(MazeCard.CANGO.LEFT) == false) {
            if(card.canGo(MazeCard.CANGO.UP) == true) {
                if(card.canGo(MazeCard.CANGO.RIGHT) == false) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        //System.out.print("║|");
                        try {
                            label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/wayL_false_true_false_true.jpg"))));
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if(card.canGo(MazeCard.CANGO.RIGHT) == true) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        //System.out.print("╠|");
                        try {
                            label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/wayF_false_true_true_true.jpg"))));
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(card.canGo(MazeCard.CANGO.DOWN) == false) {
                        //System.out.print("╚|");
                        try {
                            label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/wayC_false_true_true_false.jpg"))));
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }                           
            } 
            else if(card.canGo(MazeCard.CANGO.UP) == false) {
                if(card.canGo(MazeCard.CANGO.RIGHT) == true) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        //System.out.print("╔|");
                        try {
                            label.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/wayC_false_false_true_true.jpg"))));
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return label;
    }
}

/**
 * This class represents cleaned paths
 */
class BgPanel extends JPanel implements Serializable {
    public transient Image bg;
    
    /**
     * Constructor sets the cards.
     * @param card specific card.
     */
    public BgPanel(MazeCard card){
        //bg = new ImageIcon(ImageIO.read(new File(path + "/lib/wayL_true_false_true_false.jpg"))).getImage();
            
        if(card.canGo(MazeCard.CANGO.LEFT) == true) {
            if(card.canGo(MazeCard.CANGO.UP) == false) {
                if(card.canGo(MazeCard.CANGO.RIGHT) == true) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == false) {
                        //System.out.print("═|");
                        try {
                            bg = new ImageIcon(ImageIO.read(new File(path + "/lib/wayL_true_false_true_false.jpg"))).getImage();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        //System.out.print("╦|");
                        try {
                            bg = new ImageIcon(ImageIO.read(new File(path + "/lib/wayF_true_false_true_true.jpg"))).getImage();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if(card.canGo(MazeCard.CANGO.RIGHT) == false) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        //System.out.print("╗|");
                        try {
                            bg = new ImageIcon(ImageIO.read(new File(path + "/lib/wayC_true_false_false_true.jpg"))).getImage();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }           
            else if(card.canGo(MazeCard.CANGO.UP) == true) {
                if(card.canGo(MazeCard.CANGO.RIGHT) == true) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == false) {
                        //System.out.print("╩|");
                        try {
                            bg = new ImageIcon(ImageIO.read(new File(path + "/lib/wayF_true_true_true_false.jpg"))).getImage();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if(card.canGo(MazeCard.CANGO.RIGHT) == false) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        //System.out.print("╣|");
                        try {
                            bg = new ImageIcon(ImageIO.read(new File(path + "/lib/wayF_true_true_false_true.jpg"))).getImage();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(card.canGo(MazeCard.CANGO.DOWN) == false) {
                        //System.out.print("╝|");
                        try {
                            bg = new ImageIcon(ImageIO.read(new File(path + "/lib/wayC_true_true_false_false.jpg"))).getImage();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        else if(card.canGo(MazeCard.CANGO.LEFT) == false) {
            if(card.canGo(MazeCard.CANGO.UP) == true) {
                if(card.canGo(MazeCard.CANGO.RIGHT) == false) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        //System.out.print("║|");
                        try {
                            bg = new ImageIcon(ImageIO.read(new File(path + "/lib/wayL_false_true_false_true.jpg"))).getImage();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if(card.canGo(MazeCard.CANGO.RIGHT) == true) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        //System.out.print("╠|");
                        try {
                            bg = new ImageIcon(ImageIO.read(new File(path + "/lib/wayF_false_true_true_true.jpg"))).getImage();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(card.canGo(MazeCard.CANGO.DOWN) == false) {
                        //System.out.print("╚|");
                        try {
                            bg = new ImageIcon(ImageIO.read(new File(path + "/lib/wayC_false_true_true_false.jpg"))).getImage();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }                           
            } 
            else if(card.canGo(MazeCard.CANGO.UP) == false) {
                if(card.canGo(MazeCard.CANGO.RIGHT) == true) {
                    if(card.canGo(MazeCard.CANGO.DOWN) == true) {
                        //System.out.print("╔|");
                        try {
                            bg = new ImageIcon(ImageIO.read(new File(path + "/lib/wayC_false_false_true_true.jpg"))).getImage();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}