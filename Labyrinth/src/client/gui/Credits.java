/*
 * IJA 2015: The Labyrinth
 */
package client.gui;

import static client.client.gui;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author xpospi73, xdress00
 */

/**
 * This class is for credits show.
 */
public class Credits implements Serializable {
    public JButton buttonCreditsBack = new JButton("Back");
    
    private final JLabel labelCreditsTitle = new JLabel("Credits");
    private final JLabel labelCreditsContent = new JLabel("Miroslav Pospíšil (xpospi73) & David Dressler (xdress00)");
    private final JLabel labelCreditsYear = new JLabel("2015, FIT VUT Brno");
    
    public JPanel panelCreditsTitle = new JPanel();
    public JPanel panelCreditsContent = new JPanel();
    
    private final BoxLayout boxLayoutCredits = new BoxLayout(panelCreditsContent, BoxLayout.Y_AXIS);
    
    public Credits() {
        panelCreditsTitle.setBorder(new EmptyBorder(50,50,50,50));
        panelCreditsTitle.setBackground(new Color(0,0,0, (float) 0.5));
        panelCreditsContent.setBackground(new Color(0,0,0, (float) 0.5));
        
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
                gui.remove(panelCreditsTitle);
                gui.remove(panelCreditsContent);
                gui.setMenu();
            }
        });        
        
        panelCreditsTitle.add(labelCreditsTitle);
        panelCreditsTitle.add(Box.createRigidArea(new Dimension(0,50)));
        panelCreditsContent.add(labelCreditsContent);
        panelCreditsContent.add(Box.createRigidArea(new Dimension(0,40)));
        panelCreditsContent.add(labelCreditsYear);
        panelCreditsContent.add(Box.createRigidArea(new Dimension(0,40)));
        panelCreditsContent.add(buttonCreditsBack);
        
        panelCreditsContent.setLayout(boxLayoutCredits);
    }
}
