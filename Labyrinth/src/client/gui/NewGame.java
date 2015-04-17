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
public class NewGame {
    public JButton buttonNewGameStartGame = new JButton("Start Game");
    public JButton buttonNewGameBack = new JButton("Back");
    
    private final JLabel labelNewGame = new JLabel("New Game");
    
    public JPanel panelNewGameTitle = new JPanel();
    public JPanel panelNewGameControls = new JPanel();
    
    public NewGame() {
        panelNewGameTitle.setBorder(new EmptyBorder(50,50,50,50));
        panelNewGameTitle.setBackground(new Color(0,0,0, (float) 0.5));
        panelNewGameControls.setBackground(new Color(0,0,0, (float) 0.5));
        
        labelNewGame.setFont(new Font("Calibri", Font.PLAIN, 50));
        labelNewGame.setForeground(Color.WHITE);
        panelNewGameTitle.add(labelNewGame);
        
        buttonNewGameBack.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonNewGameBack.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.remove(panelNewGameTitle);
                gui.remove(panelNewGameControls);
                gui.setMenu();
            }
        });
        
        panelNewGameControls.add(buttonNewGameStartGame);        
        panelNewGameControls.add(buttonNewGameBack);
    }
}
