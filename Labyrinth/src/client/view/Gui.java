/*
 * IJA 2015: The Labyrinth
 */
package client.view;


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
public class Gui extends JFrame {
    public JButton buttonMenuNewGame = new JButton("New Game");
    public JButton buttonMenuLoadGame = new JButton("Load Game");
    public JButton buttonMenuHelp = new JButton("Help");
    public JButton buttonMenuCredits = new JButton("Credits");
    public JButton buttonMenuExit = new JButton("Exit");
    public JButton buttonNewGameStartGame = new JButton("Start Game");
    public JButton buttonNewGameBack = new JButton("Back");
    
    private JLabel labelMenuTheLabyrinth = new JLabel("The Labyrinth");
    private JLabel labelNewGame = new JLabel("New Game");
    
    private JPanel panelMenuTitle = new JPanel();
    private JPanel panelMenuButtons = new JPanel();
    private JPanel panelNewGameTitle = new JPanel();
    private JPanel panelNewGameControls = new JPanel();
    
    String path = System.getProperty("user.dir");
    
    public Gui() {
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
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,700);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    public void Menu(){
        panelMenuTitle.setBorder(new EmptyBorder(50,50,50,50));
        panelMenuTitle.setBackground(new Color(0,0,0, (float) 0.5));
        add(panelMenuTitle);
        
        panelMenuButtons.setBackground(new Color(0,0,0, (float) 0.5));
        add(panelMenuButtons);
    
        labelMenuTheLabyrinth.setFont(new Font("Calibri", Font.PLAIN, 50));
        labelMenuTheLabyrinth.setForeground(Color.WHITE);
        panelMenuTitle.add(labelMenuTheLabyrinth);
        
        buttonMenuNewGame.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panelMenuTitle);
                remove(panelMenuButtons);
                NewGame();
            }
            
        });
        
        buttonMenuExit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        panelMenuButtons.add(buttonMenuNewGame);
        panelMenuButtons.add(buttonMenuLoadGame);
        panelMenuButtons.add(buttonMenuHelp);
        panelMenuButtons.add(buttonMenuCredits);
        panelMenuButtons.add(buttonMenuExit);
        
        validate();
        repaint();
    }
    
    public void NewGame (){
        panelNewGameTitle.setBorder(new EmptyBorder(50,50,50,50));
        panelNewGameTitle.setBackground(new Color(0,0,0, (float) 0.5));
        add(panelNewGameTitle);
        
        panelNewGameControls.setBackground(new Color(0,0,0, (float) 0.5));
        add(panelNewGameControls);
    
        labelNewGame.setFont(new Font("Calibri", Font.PLAIN, 50));
        labelNewGame.setForeground(Color.WHITE);
        panelNewGameTitle.add(labelNewGame);
        
        buttonNewGameBack.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panelNewGameTitle);
                remove(panelNewGameControls);
                Menu();
            }
        });
        
        panelNewGameControls.add(buttonNewGameStartGame);        
        panelNewGameControls.add(buttonNewGameBack);
        
        validate();
        repaint();
    }
}
