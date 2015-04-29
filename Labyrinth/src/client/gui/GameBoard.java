/*
 * IJA 2015: The Labyrinth
 */
package client.gui;

import client.board.MazeCard;
import static client.client.gui;
import client.game.*;
import static client.gui.Gui.path;
import client.tui.Tui;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author xpospi73, xdress00
 */
public class GameBoard {
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
    
    private final JLabel labelGamePlaying = new JLabel("Playing: ");
    private final JLabel labelGamePlayer1 = new JLabel("Player 1");
    private final JLabel labelGamePlayer2 = new JLabel("Player 2");
    private final JLabel labelGamePlayer3 = new JLabel("Player 3");
    private final JLabel labelGamePlayer4 = new JLabel("Player 4");
    private final JLabel labelGameHistory = new JLabel("History");
    private final JLabel labelGameControls = new JLabel("Controls");
    private final JLabel labelGameTreasure = new JLabel("Treasure");
    private final JLabel labelGameFreeCard = new JLabel("Free card");
    
    public JTextField textFieldGameTurnRightCardX = new JTextField("");
    public JTextField textFieldGameTurnRightCardY = new JTextField("");
    public JTextField textFieldGameTurnLeftCardX = new JTextField("");
    public JTextField textFieldGameTurnLeftCardY = new JTextField("");
    public JTextField textFieldGameShiftRight = new JTextField("");
    public JTextField textFieldGameShiftLeft = new JTextField("");
    public JTextField textFieldGameShiftDown = new JTextField("");
    public JTextField textFieldGameShiftUp = new JTextField("");
    public JTextField textFieldGameGoX = new JTextField("");
    public JTextField textFieldGameGoY = new JTextField("");
    
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
    
    private final BoxLayout boxLayoutGameBoard = new BoxLayout(panelGameBoard, BoxLayout.Y_AXIS);
    private final BoxLayout boxLayoutGameHistory = new BoxLayout(panelGameHistory, BoxLayout.Y_AXIS);
    private final BoxLayout boxLayoutGameHistoryField = new BoxLayout(panelGameHistoryField, BoxLayout.Y_AXIS);
    private final BoxLayout boxLayoutGameControlsButtonsLeftInner = new BoxLayout(panelGameControlsButtonsLeftInner, BoxLayout.Y_AXIS);
    private final BoxLayout boxLayoutGameControlsButtonsRightInner = new BoxLayout(panelGameControlsButtonsRightInner, BoxLayout.Y_AXIS);
    private final BoxLayout boxLayoutGameTreasure = new BoxLayout(panelGameTreasure, BoxLayout.Y_AXIS);
    private final BoxLayout boxLayoutGameFreeCard = new BoxLayout(panelGameFreeCard, BoxLayout.Y_AXIS);
    
    public GameBoard(){
        panelGameHeader.setBackground(new Color(0,0,0, (float) 0.5));
        panelGameHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.WHITE));
        
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
                
        buttonGameUndo.setFont(new Font("Calibri", Font.BOLD, 20));
                
        labelGamePlaying.setFont(new Font("Calibri", Font.BOLD, 20));
        labelGamePlaying.setForeground(Color.WHITE);
        
        labelGamePlayer1.setFont(new Font("Calibri", Font.BOLD, 20));
        labelGamePlayer1.setForeground(Color.WHITE);
        
        labelGamePlayer2.setFont(new Font("Calibri", Font.BOLD, 20));
        labelGamePlayer2.setForeground(Color.WHITE);
              
        labelGamePlayer3.setFont(new Font("Calibri", Font.BOLD, 20));
        labelGamePlayer3.setForeground(Color.WHITE);
        
        labelGamePlayer4.setFont(new Font("Calibri", Font.BOLD, 20));
        labelGamePlayer4.setForeground(Color.WHITE);
        
        panelGameHeader.add(buttonGameMenu);
        panelGameHeader.add(Box.createRigidArea(new Dimension(10,0)));
        panelGameHeader.add(buttonGameSave);
        panelGameHeader.add(Box.createRigidArea(new Dimension(10,0)));
        panelGameHeader.add(buttonGameUndo);
        panelGameHeader.add(Box.createRigidArea(new Dimension(200,0)));
        panelGameHeader.add(labelGamePlaying);
        panelGameHeader.add(Box.createRigidArea(new Dimension(10,0)));
        panelGameHeader.add(labelGamePlayer1);
        
        /*************************************************************************/
        
        panelGame.setBackground(new Color(0,0,0, (float) 0.5));
        
        panelGameBoard.setBackground(new Color(0,0,0, (float) 0.0));
        
        panelGameControls.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControls.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.WHITE));
        
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
                        
        panelGameHistory.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameHistory.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.WHITE));
        
        panelGameHistoryField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panelGameHistoryField.setMinimumSize(new Dimension(250, 200));
        panelGameHistoryField.setMaximumSize(new Dimension(250, 200));
        panelGameHistoryField.setAlignmentX(Component.CENTER_ALIGNMENT);
                
        panelGameControlsButtons.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsButtons.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.WHITE));
        
        panelGameTreasureFreeCard.setBackground(new Color(0,0,0, (float) 0.0));
        
        panelGameTreasure.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameTreasure.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.WHITE));
        
        panelGameFreeCard.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameFreeCard.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        
        panelGame.add(panelGameBoard, BorderLayout.WEST);
        panelGame.add(panelGameControls, BorderLayout.EAST);
        
        panelGameControls.setLayout(new GridLayout(3,1));   
        panelGameControls.add(panelGameHistory, BorderLayout.NORTH);
        panelGameControls.add(panelGameControlsButtons, BorderLayout.CENTER);
        panelGameControls.add(panelGameTreasureFreeCard, BorderLayout.SOUTH);

        panelGameControlsButtons.setLayout(new GridLayout(1,2));
        panelGameControlsButtons.add(panelGameControlsButtonsLeft, BorderLayout.WEST);
        panelGameControlsButtons.add(panelGameControlsButtonsRight, BorderLayout.EAST);
        
        panelGameControlsButtonsLeft.add(panelGameControlsButtonsLeftInner);
        panelGameControlsButtonsRight.add(panelGameControlsButtonsRightInner);
        
        panelGameTreasureFreeCard.setLayout(new GridLayout(1,2));
        panelGameTreasureFreeCard.add(panelGameTreasure, BorderLayout.WEST);
        panelGameTreasureFreeCard.add(panelGameFreeCard, BorderLayout.EAST);
        
        /*************************************************************************/
        
        //panelGameBoard.add(Box.createRigidArea(new Dimension(0,570)));
        
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
        
        buttonGameTurnLeftCard.setFont(new Font("Calibri", Font.BOLD, 13));
        buttonGameTurnLeftCard.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonGameTurnLeftCard.setMargin(new Insets(1,1,1,1));
        
        buttonGameTurnRightFreeCard.setFont(new Font("Calibri", Font.BOLD, 13));
        buttonGameTurnRightFreeCard.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonGameTurnRightFreeCard.setMargin(new Insets(1,1,1,1));
        
        buttonGameTurnLeftFreeCard.setFont(new Font("Calibri", Font.BOLD, 13));
        buttonGameTurnLeftFreeCard.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonGameTurnLeftFreeCard.setMargin(new Insets(1,1,1,1));
        
        buttonGameShiftRight.setFont(new Font("Calibri", Font.BOLD, 13));
        buttonGameShiftRight.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonGameShiftRight.setMargin(new Insets(1,1,1,1));
        
        buttonGameShiftLeft.setFont(new Font("Calibri", Font.BOLD, 13));
        buttonGameShiftLeft.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonGameShiftLeft.setMargin(new Insets(1,1,1,1));
        
        buttonGameShiftDown.setFont(new Font("Calibri", Font.BOLD, 13));
        buttonGameShiftDown.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonGameShiftDown.setMargin(new Insets(1,1,1,1));
        
        buttonGameShiftUp.setFont(new Font("Calibri", Font.BOLD, 13));
        buttonGameShiftUp.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonGameShiftUp.setMargin(new Insets(1,1,1,1));
        
        buttonGameGo.setFont(new Font("Calibri", Font.BOLD, 15));
        buttonGameGo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
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
        
                        
        panelGameHistory.add(labelGameHistory);
        panelGameHistory.add(Box.createRigidArea(new Dimension(350,10)));
        panelGameHistory.add(panelGameHistoryField);
        panelGameHistory.add(Box.createRigidArea(new Dimension(0,10)));
        
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
        
        panelGameTreasure.add(labelGameTreasure);
        
        panelGameFreeCard.add(labelGameFreeCard);
        
        panelGameBoard.setLayout(boxLayoutGameBoard);
        panelGameHistory.setLayout(boxLayoutGameHistory);
        panelGameHistoryField.setLayout(boxLayoutGameHistoryField);
        panelGameControlsButtonsLeftInner.setLayout(boxLayoutGameControlsButtonsLeftInner);
        panelGameControlsButtonsRightInner.setLayout(boxLayoutGameControlsButtonsRightInner);
        panelGameTreasure.setLayout(boxLayoutGameTreasure);
        panelGameFreeCard.setLayout(boxLayoutGameFreeCard);
    }
    
    void printGameMatrix(Game game){
        JPanel panelBoard = new JPanel();
        panelBoard.setLayout(new GridLayout(game.boardSize + 2, game.boardSize + 2));
        panelBoard.setBackground(new Color(0,0,0, (float) 0.0));
        JPanel[][] panel = new JPanel[(game.boardSize + 2)][(game.boardSize + 2)];
       
        JPanel blank[] = new JPanel[3];
        blank[0] = new JPanel();
        blank[0].setBackground(new Color(0,0,0, (float) 0.0));
        panelBoard.add(blank[0]);
        
        for (int c = 1; c <= game.boardSize; c++){
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
        
        for (int r = 1; r <= game.boardSize; r++){
            for (int c = 0; c < game.boardSize + 2; c++) {
                if(c == 0){
                    panel[r][0] = new JPanel();
                    //panel[r][0].setPreferredSize(new Dimension(40,40));
                    panel[r][0].setBackground(new Color(0,0,0, (float) 0.0));
                    panel[r][0].setBorder(new EmptyBorder(10,10,0,0));
                    JLabel num = new JLabel(Integer.toString(r));
                    num.setFont(new Font("Calibri", Font.BOLD, 20));
                    num.setForeground(Color.WHITE);
                    panel[r][0].add(num);
                    panelBoard.add(panel[r][0]);
                }
                else if(c == game.boardSize + 1){
                    panel[r][game.boardSize + 1] = new JPanel();
                    //panel[r][game.boardSize + 1].setPreferredSize(new Dimension(50,50));
                    panel[r][game.boardSize + 1].setBackground(new Color(0,0,0, (float) 0.0));
                    panel[r][game.boardSize + 1].setBorder(new EmptyBorder(10,0,0,10));
                    JLabel num = new JLabel(Integer.toString(r));
                    num.setFont(new Font("Calibri", Font.BOLD, 20));
                    num.setForeground(Color.WHITE);
                    panel[r][game.boardSize + 1].add(num);
                    panelBoard.add(panel[r][game.boardSize + 1]);
                }
                else {
                    panel[r][c] = new JPanel();
                    panel[r][c].setPreferredSize(new Dimension(50,50));
                    panel[r][c].setBackground(new Color(0,0,0, (float) 0.0));
                    //panel[r][c].setBorder(new EmptyBorder(0,0,10,0));
                    panel[r][c].add(printImage(Game.mazeboard.get(r, c).getCard()));
                    panelBoard.add(panel[r][c]);
                }
            }
        }
        
        blank[2] = new JPanel();
        blank[2].setBackground(new Color(0,0,0, (float) 0.0));
        panelBoard.add(blank[2]);
        
        for (int c = 1; c <= game.boardSize; c++){
            panel[game.boardSize + 1][c] = new JPanel();
            panel[game.boardSize + 1][c].setBackground(new Color(0,0,0, (float) 0.0));
            panel[game.boardSize + 1][c].setBorder(new EmptyBorder(0,0,20,0));
            JLabel num = new JLabel(Integer.toString(c));
            num.setFont(new Font("Calibri", Font.BOLD, 20));
            num.setForeground(Color.WHITE);
            panel[game.boardSize + 1][c].add(num);
            panelBoard.add(panel[game.boardSize + 1][c]);
        }
        
        switch(game.boardSize){
            case 5:     panelGameBoard.setBorder(new EmptyBorder(170,200,170,0));
                        break;
            case 7:     panelGameBoard.setBorder(new EmptyBorder(120,147,120,0));
                        break;
            case 9:     panelGameBoard.setBorder(new EmptyBorder(70,96,70,0));
                        break;
            case 11:    panelGameBoard.setBorder(new EmptyBorder(0,70,0,0));
                        break;
        }
        panelGameBoard.add(panelBoard);
    }
    
    private static JLabel printImage(MazeCard card) {
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
