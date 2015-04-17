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
public class LoadGame {
    public JButton buttonCreditsBack = new JButton("Back");
    
    private final JLabel labelCreditsTitle = new JLabel("Load Game");
    private final JLabel labelCreditsContent = new JLabel("Miroslav Pospíšil (xpospi73) & David Dressler (xdress00)");
    private final JLabel labelCreditsYear = new JLabel("2015, FIT VUT Brno");
    
    public JPanel panelLoadGameTitle = new JPanel();
    public JPanel panelLoadGameContent = new JPanel();
    
    private final BoxLayout boxLayoutCredits = new BoxLayout(panelLoadGameContent, BoxLayout.Y_AXIS);
    
    public LoadGame() {
        panelLoadGameTitle.setBorder(new EmptyBorder(50,50,50,50));
        panelLoadGameTitle.setBackground(new Color(0,0,0, (float) 0.5));
        panelLoadGameContent.setBackground(new Color(0,0,0, (float) 0.5));
        
        labelCreditsTitle.setFont(new Font("Calibri", Font.PLAIN, 50));
        labelCreditsTitle.setForeground(Color.WHITE);
        labelCreditsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        labelCreditsContent.setFont(new Font("Calibri", Font.PLAIN, 30));
        labelCreditsContent.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelCreditsContent.setForeground(Color.WHITE);
        
        labelCreditsYear.setFont(new Font("Calibri", Font.PLAIN, 30));
        labelCreditsYear.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelCreditsYear.setForeground(Color.WHITE);
        
        buttonCreditsBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonCreditsBack.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonCreditsBack.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.remove(panelLoadGameTitle);
                gui.remove(panelLoadGameContent);
                gui.setMenu();
            }
        });        
        
        panelLoadGameTitle.add(labelCreditsTitle);
        panelLoadGameTitle.add(Box.createRigidArea(new Dimension(0,50)));
        panelLoadGameContent.add(labelCreditsContent);
        panelLoadGameContent.add(Box.createRigidArea(new Dimension(0,40)));
        panelLoadGameContent.add(labelCreditsYear);
        panelLoadGameContent.add(Box.createRigidArea(new Dimension(0,40)));
        panelLoadGameContent.add(buttonCreditsBack);
        
        panelLoadGameContent.setLayout(boxLayoutCredits);
    }
}
