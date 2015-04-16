/*
 * IJA 2015: The Labyrinth
 */
package client.view;

import client.view.menu.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author xpospi73, xdress00
 */
public class Menu extends JFrame {
    public JButton buttonNewGame;
    public JButton buttonLoadGame;
    public JButton buttonHelp;
    public JButton buttonCredits;
    public JButton buttonExit;
    
    private final JLabel labelTheLabyrinth;
    
    private final JPanel panelTitle;
    private final JPanel panelButtons;
    
    String path = System.getProperty("user.dir");
    
    public Menu() {
        super("The Labyrinth");
        
        GridLayout layoutMenu;
        layoutMenu = new GridLayout(2,1);
        
        JLabel contentPane = new JLabel();
        try {
            contentPane.setIcon(new ImageIcon(ImageIO.read(new File(path + "/lib/Labyrinth.jpg"))));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        setContentPane(contentPane);
        
        setLayout(layoutMenu);
        
        panelTitle = new JPanel();
        panelButtons = new JPanel();
        
        panelTitle.setBorder(new EmptyBorder(50,50,50,50));
        panelTitle.setBackground(new Color(0,0,0, (float) 0.5));
        add(panelTitle);
        
        panelButtons.setBackground(new Color(0,0,0, (float) 0.5));
        add(panelButtons);
    
        labelTheLabyrinth = new JLabel("The Labyrinth");
        labelTheLabyrinth.setFont(new Font("Calibri", Font.PLAIN, 50));
        labelTheLabyrinth.setForeground(Color.WHITE);
        panelTitle.add(labelTheLabyrinth);
        
        buttonNewGame = new JButton("New Game");
        buttonLoadGame = new JButton("Load Game");
        buttonHelp = new JButton("Help");
        buttonCredits = new JButton("Credits");
        buttonExit = new JButton("Exit");
        
        buttonNewGame.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                NewGame newGameView = new NewGame();
            }
            
        });
            
        
        panelButtons.add(buttonNewGame);
        panelButtons.add(buttonLoadGame);
        panelButtons.add(buttonHelp);
        panelButtons.add(buttonCredits);
        panelButtons.add(buttonExit);
        
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,700);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
