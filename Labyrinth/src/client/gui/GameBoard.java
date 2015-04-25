/*
 * IJA 2015: The Labyrinth
 */
package client.gui;

import static client.client.gui;
import java.awt.*;
import java.awt.event.*;
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
    private final JLabel labelGameHr = new JLabel("----------------------------------------");
    private final JLabel labelGameTreasure = new JLabel("Treasure");
    private final JLabel labelGameFreeCard = new JLabel("Free card");
    
    public JPanel panelGameHeader = new JPanel();
    public JPanel panelGame = new JPanel(new BorderLayout());
    public JPanel panelGameBoard = new JPanel();
    public JPanel panelGameControls = new JPanel();
    public JPanel panelGameHistory = new JPanel();
    public JPanel panelGameHistoryField = new JPanel();
    public JPanel panelGameControlsButtons = new JPanel();
    public JPanel panelGameControlsButtonsLeft = new JPanel();
    public JPanel panelGameControlsButtonsRight = new JPanel();
    public JPanel panelGameTreasureFreeCard = new JPanel();
    public JPanel panelGameTreasure = new JPanel();
    public JPanel panelGameFreeCard = new JPanel();
    
    private final BoxLayout boxLayoutGameBoard = new BoxLayout(panelGameBoard, BoxLayout.Y_AXIS);
    private final BoxLayout boxLayoutGameHistory = new BoxLayout(panelGameHistory, BoxLayout.Y_AXIS);
    private final BoxLayout boxLayoutGameHistoryField = new BoxLayout(panelGameHistoryField, BoxLayout.Y_AXIS);
    private final BoxLayout boxLayoutGameControlsButtonsLeft = new BoxLayout(panelGameControlsButtonsLeft, BoxLayout.Y_AXIS);
    private final BoxLayout boxLayoutGameControlsButtonsRight = new BoxLayout(panelGameControlsButtonsRight, BoxLayout.Y_AXIS);
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
        
        panelGameBoard.setBorder(new EmptyBorder(20,20,20,20));
        panelGameBoard.setBackground(new Color(0,0,0, (float) 0.0));
        
        panelGameControls.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControls.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.WHITE));
        
        panelGameControlsButtonsLeft.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsButtonsLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.WHITE));
        panelGameControlsButtonsLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelGameControlsButtonsRight.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsButtonsRight.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        panelGameControlsButtonsRight.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
        panelGameHistory.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameHistory.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.WHITE));
        
        panelGameHistoryField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panelGameHistoryField.setMinimumSize(new Dimension(250, 200));
        panelGameHistoryField.setMaximumSize(new Dimension(250, 200));
        panelGameHistoryField.setAlignmentX(Component.CENTER_ALIGNMENT);
                
        panelGameControlsButtons.setBackground(new Color(0,0,0, (float) 0.0));
        panelGameControlsButtons.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.WHITE));
        
        //panelGameTreasureFreeCard.setBorder(new EmptyBorder(10,10,10,10));
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
        
        panelGameTreasureFreeCard.setLayout(new GridLayout(1,2));
        panelGameTreasureFreeCard.add(panelGameTreasure, BorderLayout.WEST);
        panelGameTreasureFreeCard.add(panelGameFreeCard, BorderLayout.EAST);
        
        /*************************************************************************/
        
        panelGameBoard.add(Box.createRigidArea(new Dimension(0,570)));
        
        /*************************************************************************/
        
        labelGameHistory.setFont(new Font("Calibri", Font.BOLD, 20));
        labelGameHistory.setForeground(Color.WHITE);
        labelGameHistory.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        labelGameControls.setFont(new Font("Calibri", Font.BOLD, 20));
        labelGameControls.setForeground(Color.WHITE);
        labelGameControls.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelGameControls.setBorder(new EmptyBorder(10,50,0,0));
        
        labelGameHr.setForeground(Color.WHITE);
        labelGameHr.setBorder(new EmptyBorder(10,5,0,0));
        
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
                        
        panelGameHistory.add(labelGameHistory);
        panelGameHistory.add(Box.createRigidArea(new Dimension(350,10)));
        panelGameHistory.add(panelGameHistoryField);
        panelGameHistory.add(Box.createRigidArea(new Dimension(0,10)));
        
        panelGameControlsButtonsLeft.add(labelGameControls);
        panelGameControlsButtonsLeft.add(Box.createRigidArea(new Dimension(0,10)));
        panelGameControlsButtonsLeft.add(buttonGameTurnRightCard);
        panelGameControlsButtonsLeft.add(Box.createRigidArea(new Dimension(0,10)));
        panelGameControlsButtonsLeft.add(buttonGameTurnLeftCard);
        panelGameControlsButtonsLeft.add(Box.createRigidArea(new Dimension(0,10)));
        panelGameControlsButtonsLeft.add(buttonGameTurnRightFreeCard);
        panelGameControlsButtonsLeft.add(Box.createRigidArea(new Dimension(0,10)));
        panelGameControlsButtonsLeft.add(buttonGameTurnLeftFreeCard);
        panelGameControlsButtonsLeft.add(Box.createRigidArea(new Dimension(0,10)));
        panelGameControlsButtonsRight.add(buttonGameShiftRight);
        panelGameControlsButtonsRight.add(Box.createRigidArea(new Dimension(0,10)));
        panelGameControlsButtonsRight.add(buttonGameShiftLeft);
        panelGameControlsButtonsRight.add(Box.createRigidArea(new Dimension(0,10)));
        panelGameControlsButtonsRight.add(buttonGameShiftDown);
        panelGameControlsButtonsRight.add(Box.createRigidArea(new Dimension(0,10)));
        panelGameControlsButtonsRight.add(buttonGameShiftUp);
        //panelGameControlsButtonsRight.add(Box.createRigidArea(new Dimension(0,10)));
        panelGameControlsButtonsRight.add(labelGameHr);
        panelGameControlsButtonsRight.add(buttonGameGo);
        //panelGameControlsButtonsRight.add(Box.createRigidArea(new Dimension(0,10)));
        
        panelGameTreasure.add(labelGameTreasure);
        
        panelGameFreeCard.add(labelGameFreeCard);
        
        panelGameBoard.setLayout(boxLayoutGameBoard);
        panelGameHistory.setLayout(boxLayoutGameHistory);
        panelGameHistoryField.setLayout(boxLayoutGameHistoryField);
        panelGameControlsButtonsLeft.setLayout(boxLayoutGameControlsButtonsLeft);
        panelGameControlsButtonsRight.setLayout(boxLayoutGameControlsButtonsRight);
        panelGameTreasure.setLayout(boxLayoutGameTreasure);
        panelGameFreeCard.setLayout(boxLayoutGameFreeCard);
    }
}
