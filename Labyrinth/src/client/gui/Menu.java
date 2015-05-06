/*
 * IJA 2015: The Labyrinth
 */
package client.gui;

import static client.client.gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.*;

/**
 *
 * @author xpospi73, xdress00
 */

/**
 * This class represents menu with game options.
 */
public class Menu implements Serializable {
    /**
     * Start new game.
     */
    public JButton buttonMenuNewGame = new JButton("New Game");
    
    /**
     * Load saved games.
     */
    public JButton buttonMenuLoadGame = new JButton("Load Game");
    
    /**
     * Show help.
     */
    public JButton buttonMenuHelp = new JButton("Help");
    
    /**
     * Show credits.
     */
    public JButton buttonMenuCredits = new JButton("Credits");
    
    /**
     * Exit application.
     */
    public JButton buttonMenuExit = new JButton("Exit");
    
    private final JLabel labelMenuTheLabyrinth = new JLabel("The Labyrinth");
    
    public JPanel panelMenuTitle = new JPanel();
    public JPanel panelMenuButtons = new JPanel();
    
    private final BoxLayout boxLayoutMenu = new BoxLayout(panelMenuButtons, BoxLayout.Y_AXIS);
    
    /**
     * Constructor initializes Gui for menu.
     */
    public Menu(){
        //panelMenuTitle.setBorder(new EmptyBorder(10,500,10,500));
        panelMenuTitle.setBackground(new Color(0,0,0, (float) 0.5));
        panelMenuButtons.setBackground(new Color(0,0,0, (float) 0.5));
        
        
        labelMenuTheLabyrinth.setFont(new Font("Calibri", Font.ITALIC, 50));
        labelMenuTheLabyrinth.setForeground(Color.WHITE);
        panelMenuTitle.add(labelMenuTheLabyrinth);
        
        buttonMenuNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonMenuNewGame.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonMenuNewGame.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.remove(panelMenuTitle);
                gui.remove(panelMenuButtons);
                gui.setNewGame();
            }
            
        });
        
        buttonMenuLoadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonMenuLoadGame.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonMenuLoadGame.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.remove(panelMenuTitle);
                gui.remove(panelMenuButtons);
                gui.setLoadGame();
            }
        });
        
        buttonMenuHelp.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonMenuHelp.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonMenuHelp.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.remove(panelMenuTitle);
                gui.remove(panelMenuButtons);
                gui.setHelp();
            }
        });
        
        buttonMenuCredits.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonMenuCredits.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonMenuCredits.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.remove(panelMenuTitle);
                gui.remove(panelMenuButtons);
                gui.setCredits();
            }
        });
        
        buttonMenuExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonMenuExit.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonMenuExit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        panelMenuButtons.add(buttonMenuNewGame);
        panelMenuButtons.add(Box.createRigidArea(new Dimension(0,20)));
        panelMenuButtons.add(buttonMenuLoadGame);
        panelMenuButtons.add(Box.createRigidArea(new Dimension(0,20)));
        panelMenuButtons.add(buttonMenuHelp);
        panelMenuButtons.add(Box.createRigidArea(new Dimension(0,20)));
        panelMenuButtons.add(buttonMenuCredits);
        panelMenuButtons.add(Box.createRigidArea(new Dimension(0,20)));
        panelMenuButtons.add(buttonMenuExit);
        
        panelMenuButtons.setLayout(boxLayoutMenu);
    }
}
