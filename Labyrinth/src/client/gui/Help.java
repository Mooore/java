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
public class Help {
    public JButton buttonHelpBack = new JButton("Back");
    
    private final JLabel labelHelpTitle = new JLabel("Help");
    private final JLabel labelHelpFirst = new JLabel("<html><p style=\"margin-left:150px;\">Princip hry<br>" +
                                    "První hráč si vezme první úkolovou kartu, vyobrazený předmět vidí jen on sám<br>" +
                                    "úkolem je dostat se se svou figurkou na kámen obsahující stejný obrázek; jakmile<br>" +
                                    "hráč tento kámen obsadí, zveřejní kartu a ta se započítá do získaných karet - hráč<br>" + 
                                    "poté začíná svůj další tah otočením další úkolové karty<br>" +
                                    "figurky mohou stát na jednom kameni společně (nevyhazuje se)<br>" +
                                    "hraje se po tazích, hráči se po každém tahu střídají</p></html>");
    private final JLabel labelHelpSecond = new JLabel("<html><p style=\"margin-left:180px;\">Jeden tah<br>" +
                                    "hráč má možnost změnit rozložení bludiště tak, že posune celou řadu či sloupec kamenů<br>" +
                                    "vložením volného kamene; před vložením může volný kámen libovolně natočit; po posunutí<br>" +
                                    "zůstane na druhé straně volný kámen pro další tah<br>" +
                                    "posunovat se dají pouze sudé řádky a sudé sloupce<br>" +
                                    "pokud se vysunutím kamene vysunou i figurky, přemístí se figurky na druhou stranu, tj. na<br>" +
                                    "nově vložený kámen<br>" +
                                    "hráč v jednom tahu nejprve změní rozložení bludiště a poté posune svou figurku; figurkou může<br>" +
                                    "pouze na ty kameny, které jsou spojené volnou cestou; za těchto podmínek se může posunout na<br>" +
                                    "jakýkoliv dostupný kámen (délka kroku není omezena)<br>" +
                                    "v bezprostředně následujícím tahu není povoleno provést reverzi předchozího tahu (např. posunul<br>" +
                                    "jsem 2. řádek doprava, ihned poté nelze posunout 2. řádek zpět doleva)<br>" +
                                    "hra končí, jakmile jeden hráč získá takový počet karet, který odpovídá podílu K / P, kde P je<br>" +
                                    "počet hráčů; tento hráč je vítěz</html>");
    
    public JPanel panelHelpTitle = new JPanel();
    public JPanel panelHelpContent = new JPanel();
    
    private final BoxLayout boxLayoutHelpFirst = new BoxLayout(panelHelpTitle, BoxLayout.Y_AXIS);
    private final BoxLayout boxLayoutHelpSecond = new BoxLayout(panelHelpContent, BoxLayout.Y_AXIS);
    
    public Help() {
        panelHelpTitle.setBorder(new EmptyBorder(50,50,50,50));
        panelHelpTitle.setBackground(new Color(0,0,0, (float) 0.5));
        panelHelpContent.setBackground(new Color(0,0,0, (float) 0.5));
        
        labelHelpTitle.setFont(new Font("Calibri", Font.PLAIN, 50));
        labelHelpTitle.setForeground(Color.WHITE);
        labelHelpTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        labelHelpFirst.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelHelpFirst.setForeground(Color.WHITE);
        labelHelpSecond.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelHelpSecond.setForeground(Color.WHITE);
        
        buttonHelpBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonHelpBack.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonHelpBack.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.remove(panelHelpTitle);
                gui.remove(panelHelpContent);
                gui.setMenu();
            }
        });        
        
        panelHelpTitle.add(labelHelpTitle);
        panelHelpTitle.add(Box.createRigidArea(new Dimension(0,50)));
        panelHelpTitle.add(labelHelpFirst);
        panelHelpContent.add(labelHelpSecond);
        panelHelpContent.add(Box.createRigidArea(new Dimension(0,40)));
        panelHelpContent.add(buttonHelpBack);
        
        panelHelpTitle.setLayout(boxLayoutHelpFirst);
        panelHelpContent.setLayout(boxLayoutHelpSecond);
    }
}
