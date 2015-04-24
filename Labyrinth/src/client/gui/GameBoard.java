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
    public JButton buttonNewGameStartGame = new JButton("Start Game");
    public JButton buttonNewGameBack = new JButton("Back");
    
    private final JLabel labelNewGameTitle = new JLabel("New Game");
    private final JLabel labelNewGameNumberOfPlayers = new JLabel("Number of players:");
    private final JLabel labelNewGameBoardSize = new JLabel("Board size:");
    private final JLabel labelNewGameNumberOfTreasures = new JLabel("Number of treasures:");
    
    public JRadioButton radioButtonNewGameNumberOfPlayers2 = new JRadioButton("2");
    public JRadioButton radioButtonNewGameNumberOfPlayers3 = new JRadioButton("3");
    public JRadioButton radioButtonNewGameNumberOfPlayers4 = new JRadioButton("4");
    public JRadioButton radioButtonNewGameBoardSize5 = new JRadioButton("5");
    public JRadioButton radioButtonNewGameBoardSize7 = new JRadioButton("7");
    public JRadioButton radioButtonNewGameBoardSize11 = new JRadioButton("11");
    public JRadioButton radioButtonNewGameNumberOfTreasures12 = new JRadioButton("12");
    public JRadioButton radioButtonNewGameNumberOfTreasures24 = new JRadioButton("24");
    
    public ButtonGroup radioButtonsNumberOfPlayers = new ButtonGroup();
    public ButtonGroup radioButtonsBoardSize = new ButtonGroup();
    public ButtonGroup radioButtonsNumberOfTreasures = new ButtonGroup();
    
    public JPanel panelNewGameTitle = new JPanel();
    public JPanel panelNewGameControls = new JPanel(new BorderLayout());
    public JPanel panelNewGameControlsLeft = new JPanel();
    public JPanel panelNewGameControlsRight = new JPanel();
    
    private final BoxLayout boxLayoutLeft = new BoxLayout(panelNewGameControlsLeft, BoxLayout.Y_AXIS);
    private final BoxLayout boxLayoutRight = new BoxLayout(panelNewGameControlsRight, BoxLayout.Y_AXIS);
    
    public GameBoard(){
        panelNewGameTitle.setBackground(new Color(0,0,0, (float) 0.5));
        panelNewGameControls.setBackground(new Color(0,0,0, (float) 0.5));
        
        panelNewGameControlsLeft.setBorder(new EmptyBorder(50,250,50,250));
        panelNewGameControlsLeft.setBackground(new Color(0,0,0, (float) 0.0));
        
        panelNewGameControlsRight.setBorder(new EmptyBorder(50,250,50,250));
        panelNewGameControlsRight.setBackground(new Color(0,0,0, (float) 0.0));
        
        labelNewGameTitle.setFont(new Font("Calibri", Font.PLAIN, 50));
        labelNewGameTitle.setForeground(Color.WHITE);
        panelNewGameTitle.add(labelNewGameTitle);

        panelNewGameControls.add(panelNewGameControlsLeft, BorderLayout.WEST);
        panelNewGameControls.add(panelNewGameControlsRight, BorderLayout.EAST);
        
        labelNewGameNumberOfPlayers.setFont(new Font("Calibri", Font.PLAIN, 20));
        labelNewGameNumberOfPlayers.setForeground(Color.WHITE);
        labelNewGameNumberOfPlayers.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        radioButtonsNumberOfPlayers.add(radioButtonNewGameNumberOfPlayers2);
        radioButtonsNumberOfPlayers.add(radioButtonNewGameNumberOfPlayers4);
        radioButtonsNumberOfPlayers.add(radioButtonNewGameNumberOfPlayers3);
        
        radioButtonNewGameNumberOfPlayers2.setSelected(true);
        
        radioButtonNewGameNumberOfPlayers2.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioButtonNewGameNumberOfPlayers3.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioButtonNewGameNumberOfPlayers4.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioButtonNewGameNumberOfPlayers2.setBorder(new EmptyBorder(15,40,15,40));
        radioButtonNewGameNumberOfPlayers3.setBorder(new EmptyBorder(15,40,15,40));
        radioButtonNewGameNumberOfPlayers4.setBorder(new EmptyBorder(15,40,15,40));
        radioButtonNewGameNumberOfPlayers2.setBackground(Color.WHITE);
        radioButtonNewGameNumberOfPlayers3.setBackground(Color.WHITE);
        radioButtonNewGameNumberOfPlayers4.setBackground(Color.WHITE);
        
        labelNewGameBoardSize.setFont(new Font("Calibri", Font.PLAIN, 20));
        labelNewGameBoardSize.setForeground(Color.WHITE);
        labelNewGameBoardSize.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        radioButtonsBoardSize.add(radioButtonNewGameBoardSize5);
        radioButtonsBoardSize.add(radioButtonNewGameBoardSize7);
        radioButtonsBoardSize.add(radioButtonNewGameBoardSize11);
        
        radioButtonNewGameBoardSize7.setSelected(true);
        
        radioButtonNewGameBoardSize5.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioButtonNewGameBoardSize7.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioButtonNewGameBoardSize11.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioButtonNewGameBoardSize5.setBorder(new EmptyBorder(15,40,15,40));
        radioButtonNewGameBoardSize7.setBorder(new EmptyBorder(15,40,15,40));
        radioButtonNewGameBoardSize11.setBorder(new EmptyBorder(15,35,15,38));
        radioButtonNewGameBoardSize5.setBackground(Color.WHITE);
        radioButtonNewGameBoardSize7.setBackground(Color.WHITE);
        radioButtonNewGameBoardSize11.setBackground(Color.WHITE);
        
        labelNewGameNumberOfTreasures.setFont(new Font("Calibri", Font.PLAIN, 20));
        labelNewGameNumberOfTreasures.setForeground(Color.WHITE);
        labelNewGameNumberOfTreasures.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        radioButtonsNumberOfTreasures.add(radioButtonNewGameNumberOfTreasures12);
        radioButtonsNumberOfTreasures.add(radioButtonNewGameNumberOfTreasures24);
        
        radioButtonNewGameNumberOfTreasures12.setSelected(true);
        
        radioButtonNewGameNumberOfTreasures12.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioButtonNewGameNumberOfTreasures24.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioButtonNewGameNumberOfTreasures12.setBorder(new EmptyBorder(15,40,15,40));
        radioButtonNewGameNumberOfTreasures24.setBorder(new EmptyBorder(15,40,15,40));
        radioButtonNewGameNumberOfTreasures12.setBackground(Color.WHITE);
        radioButtonNewGameNumberOfTreasures24.setBackground(Color.WHITE);
        
        buttonNewGameBack.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonNewGameBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonNewGameBack.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.remove(panelNewGameTitle);
                gui.remove(panelNewGameControls);
                gui.setMenu();
            }
        });
        
        buttonNewGameStartGame.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonNewGameStartGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonNewGameStartGame.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.remove(panelNewGameTitle);
                gui.remove(panelNewGameControls);
                gui.startNewGame();
            }
        });
        
        panelNewGameControlsLeft.add(labelNewGameNumberOfPlayers);
        panelNewGameControlsLeft.add(Box.createRigidArea(new Dimension(0,20)));
        panelNewGameControlsLeft.add(radioButtonNewGameNumberOfPlayers2);    
//        panelNewGameControlsLeft.add(Box.createRigidArea(new Dimension(0,20)));
        panelNewGameControlsLeft.add(radioButtonNewGameNumberOfPlayers3); 
//        panelNewGameControlsLeft.add(Box.createRigidArea(new Dimension(0,20)));
        panelNewGameControlsLeft.add(radioButtonNewGameNumberOfPlayers4); 
        panelNewGameControlsLeft.add(Box.createRigidArea(new Dimension(0,50)));
        panelNewGameControlsLeft.add(labelNewGameBoardSize);
        panelNewGameControlsLeft.add(Box.createRigidArea(new Dimension(0,20)));
        panelNewGameControlsLeft.add(radioButtonNewGameBoardSize5);    
//        panelNewGameControlsLeft.add(Box.createRigidArea(new Dimension(0,20)));
        panelNewGameControlsLeft.add(radioButtonNewGameBoardSize7); 
//        panelNewGameControlsLeft.add(Box.createRigidArea(new Dimension(0,20)));
        panelNewGameControlsLeft.add(radioButtonNewGameBoardSize11); 
        panelNewGameControlsLeft.add(Box.createRigidArea(new Dimension(0,50)));
        
        panelNewGameControlsRight.add(labelNewGameNumberOfTreasures);
        panelNewGameControlsRight.add(Box.createRigidArea(new Dimension(0,20)));
        panelNewGameControlsRight.add(radioButtonNewGameNumberOfTreasures12); 
//        panelNewGameControlsRight.add(Box.createRigidArea(new Dimension(0,20)));
        panelNewGameControlsRight.add(radioButtonNewGameNumberOfTreasures24); 
        panelNewGameControlsRight.add(Box.createRigidArea(new Dimension(0,150)));        
        panelNewGameControlsRight.add(buttonNewGameStartGame);  
        panelNewGameControlsRight.add(Box.createRigidArea(new Dimension(0,50)));
        panelNewGameControlsRight.add(buttonNewGameBack);
        
        panelNewGameControlsLeft.setLayout(boxLayoutLeft);
        panelNewGameControlsRight.setLayout(boxLayoutRight);
    }
}
