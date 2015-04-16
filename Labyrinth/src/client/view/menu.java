/*
 * IJA 2015: The Labyrinth
 */
package client.view;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author xpospi73, xdress00
 */
public class menu extends JFrame {
    public JButton buttonNewGame;
    public JButton buttonLoadGame;
    public JButton buttonHelp;
    public JButton buttonCredits;
    public JButton buttonExit;
    
    private final JLabel labelTheLabyrinth;
    
    private final JPanel panelTitle;
    private final JPanel panelButtons;
    
    String path = System.getProperty("user.dir");
    
    public menu() {
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
        
        panelButtons.add(buttonNewGame);
        panelButtons.add(buttonLoadGame);
        panelButtons.add(buttonHelp);
        panelButtons.add(buttonCredits);
        panelButtons.add(buttonExit);
    }
}
