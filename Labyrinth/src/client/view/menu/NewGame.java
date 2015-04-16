/*
 * IJA 2015: The Labyrinth
 */
package client.view.menu;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author xpospi73, xdress00
 */
public class NewGame extends JFrame {
    
    String path = System.getProperty("user.dir");
    
    private final JPanel panelTitle;
    
    public NewGame() {
        super("The Labyrinth");
        
        GridLayout layoutNewGame;
        layoutNewGame = new GridLayout(2,1);
        
        setLayout(layoutNewGame);
    
        panelTitle = new JPanel();
        panelTitle.setBackground(Color.BLUE);
        add(panelTitle);
        
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,700);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
