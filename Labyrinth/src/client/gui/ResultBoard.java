/*
 * IJA 2015: The Labyrinth
 */
package client.gui;

import static client.client.gui;
import client.game.Game;
import static client.gui.Gui.gameboard;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import javax.swing.*;

/**
 *
 * @author xpospi73, xdress00
 */

/**
 * This class is for showing results after the game finished.
 */
public class ResultBoard  extends JFrame implements Serializable {
    /**
     * Constructor sets a window layouts.
     */
    public ResultBoard(){
        GridLayout layoutResultBoard = new GridLayout(2,0);
        
        setLayout(layoutResultBoard);
        pack();
        setVisible(true);
        getContentPane().setBackground(new Color(0,0,0, (float) 0.0));
        setSize(350,450);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel panelResultBoardTitle = new JPanel();
        JPanel panelResultBoard = new JPanel();
               
        JLabel labelResultBoardTitle = new JLabel("Result board");
        JLabel labelResultBoardPlayer1 = new JLabel();
        JLabel labelResultBoardPlayer2 = new JLabel();
        JLabel labelResultBoardPlayer3 = new JLabel();
        JLabel labelResultBoardPlayer4 = new JLabel();
        
        JButton buttonResultBoardOk = new JButton("OK");
        
        BoxLayout layoutResultBoardTitle = new BoxLayout(panelResultBoardTitle, BoxLayout.Y_AXIS);
        BoxLayout layoutResultBoardPlayers = new BoxLayout(panelResultBoard, BoxLayout.Y_AXIS);
        
        /*****************************************************************/
        
        panelResultBoardTitle.setLayout(layoutResultBoardTitle);
        panelResultBoardTitle.setBackground(new Color(0,0,0));
                
        panelResultBoard.setLayout(layoutResultBoardPlayers);
        panelResultBoard.setBackground(new Color(0,0,0));
        
        labelResultBoardTitle.setForeground(Color.WHITE);
        labelResultBoardTitle.setFont(new Font("Calibri", Font.PLAIN, 36));
        labelResultBoardTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        labelResultBoardPlayer1.setForeground(Color.GREEN);
        labelResultBoardPlayer1.setFont(new Font("Calibri", Font.PLAIN, 20));
        labelResultBoardPlayer1.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        labelResultBoardPlayer2.setForeground(Color.RED);
        labelResultBoardPlayer2.setFont(new Font("Calibri", Font.PLAIN, 20));
        labelResultBoardPlayer2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        labelResultBoardPlayer3.setForeground(Color.CYAN);
        labelResultBoardPlayer3.setFont(new Font("Calibri", Font.PLAIN, 20));
        labelResultBoardPlayer3.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        labelResultBoardPlayer4.setForeground(Color.YELLOW);
        labelResultBoardPlayer4.setFont(new Font("Calibri", Font.PLAIN, 20));
        labelResultBoardPlayer4.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        buttonResultBoardOk.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonResultBoardOk.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonResultBoardOk.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.remove(gameboard.panelGameHeader);
                gui.remove(gameboard.panelGame);
                gui.setMenu();
                dispose();
            }
        });
       
        labelResultBoardPlayer1.setText("Player 1: " + String.valueOf(Game.player1.getObtainedTreasures()));
        labelResultBoardPlayer2.setText("Player 2: " + String.valueOf(Game.player2.getObtainedTreasures()));
        if(Game.numberOfPlayers == 3) {
            labelResultBoardPlayer3.setText("Player 3: " + String.valueOf(Game.player3.getObtainedTreasures()));
        }
        else if(Game.numberOfPlayers == 4) {
            labelResultBoardPlayer3.setText("Player 3: " + String.valueOf(Game.player3.getObtainedTreasures()));
            labelResultBoardPlayer4.setText("Player 4: " + String.valueOf(Game.player4.getObtainedTreasures()));
        }
        
        panelResultBoardTitle.add(Box.createRigidArea(new Dimension(10,40)));
        panelResultBoardTitle.add(labelResultBoardTitle);
        
        panelResultBoard.add(labelResultBoardPlayer1);
        panelResultBoard.add(Box.createRigidArea(new Dimension(10,10)));
        panelResultBoard.add(labelResultBoardPlayer2);
        panelResultBoard.add(Box.createRigidArea(new Dimension(10,10)));
        panelResultBoard.add(labelResultBoardPlayer3);
        panelResultBoard.add(Box.createRigidArea(new Dimension(10,10)));
        panelResultBoard.add(labelResultBoardPlayer4);
        panelResultBoard.add(Box.createRigidArea(new Dimension(10,10)));
        panelResultBoard.add(buttonResultBoardOk);
        
        add(panelResultBoardTitle);
        add(panelResultBoard);
    }
}
